(ns chromium.canvas
  (:require [chromium.utility :as util]
            [chromium.objects :as obj]
            [chromium.shaders :as s])
  (:import [System.Drawing]
           [OpenTk]
           [OpenTK.Graphics.ES30 PrimitiveType]
           [OpenTK.Graphics.OpenGL4 GL BufferTarget]
           [OpenTK.Input Keyboard Key]))

(def handler (atom nil))
(def vbo (atom nil))
(def vao (atom nil))

(defn on-load [this event]
  (let [vShader (s/load-shader ShaderType/VertexShader s/vtex-src)
        fShader (s/load-shader ShaderType/FragmentShader s/frag-src)
        triangle obj/triangle]

    (Console/WriteLine (str "OpenGL Version: "
                            (GL/GetString StringName/Version)))


    (reset! vbo (GL/GenBuffer))
    (GL/BindBuffer BufferTarget/ArrayBuffer @vbo)

    (GL/BufferData BufferTarget/ArrayBuffer obj/size-of-tri
                   obj/triangle
                   BufferUsageHint/StaticDraw)

    (reset! handler (GL/CreateProgram))

    (Console/WriteLine (GL/GetShaderInfoLog vShader))
    (Console/WriteLine (GL/GetShaderInfoLog fShader))


    (GL/AttachShader @handler vShader)
    (GL/AttachShader @handler fShader)


    (GL/LinkProgram @handler)
    (s/detachndelete @handler vShader)
    (s/detachndelete @handler fShader)


    (GL/UseProgram @handler)

    
    (reset! vao (GL/GenVertexArray))
    (GL/BindVertexArray @vao)

    (GL/VertexAttribPointer (int 0) 3 VertexAttribPointerType/Float false
                            obj/size-of-tri 0)

    (GL/EnableVertexAttribArray (int 0))
    (GL/BindBuffer BufferTarget/ArrayBuffer @vbo)))


(defn on-update-frame [this event]
  (let [input (Keyboard/GetState)]
    (when (.IsKeyDown input Key/Escape)
      (.Exit this))))

(defn on-render-frame [this event]
  (util/clear-buffer-mask)
  (GL/UseProgram @handler)
  (GL/BindVertexArray @vao)
  (GL/DrawArrays PrimitiveType/Triangles 0 3)
  ;; (GL/DrawArrays PrimitiveType/Lines 0 3)  ;; This works and draws one line.
  (.SwapBuffers this))

(defn on-unload [this event]
  (GL/BindBuffer BufferTarget/ArrayBuffer (int 0))
  (GL/DeleteProgram @handler))
