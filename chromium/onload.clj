(ns chromium.onload
  (:require [chromium.utility :as util]
            [chromium.objects :as obj]
            [chromium.shaders :as s])
  (:import [OpenTK.Graphics.OpenGL4 GL BufferTarget]))


(def handler (atom nil))
(def vbo (atom nil))
(def vao (atom nil))

(defn init []
  (reset! handler (GL/CreateProgram))
  (reset! vbo (GL/GenBuffer))
  (reset! vao (GL/GenVertexArray)))

(defn render []
   (let [vShader (s/load-shader ShaderType/VertexShader s/vtex-src)
        fShader (s/load-shader ShaderType/FragmentShader s/frag-src)]

    (GL/BindBuffer BufferTarget/ArrayBuffer @vbo)

    (GL/BufferData BufferTarget/ArrayBuffer obj/size-of-tri
                   obj/triangle
                   BufferUsageHint/StaticDraw)

    
    (GL/AttachShader @handler vShader)
    (GL/AttachShader @handler fShader)


    (GL/LinkProgram @handler)
    (s/detachndelete @handler vShader)
    (s/detachndelete @handler fShader)

    (GL/UseProgram @handler)

    
    (GL/BindVertexArray @vao)

    (GL/VertexAttribPointer (int 0) (int 3) VertexAttribPointerType/Float false 0 0)

    (GL/EnableVertexAttribArray (int 0))
    (GL/BindBuffer BufferTarget/ArrayBuffer @vbo)))
