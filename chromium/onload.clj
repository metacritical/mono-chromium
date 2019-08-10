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

(defn init-vbo []
  (GL/BindBuffer BufferTarget/ArrayBuffer @vbo)

  (GL/BufferData BufferTarget/ArrayBuffer obj/size-of-tri
                 obj/triangle
                 BufferUsageHint/StaticDraw))

(defn init-vao []
  (GL/BindVertexArray @vao)
  (GL/VertexAttribPointer (int 0) 3 VertexAttribPointerType/Float false 0 0)
  (GL/EnableVertexAttribArray (int 0)))

(defn delete-shader [shader]
  (GL/DetachShader @handler shader)
  (GL/DeleteShader shader))


(defn render []
   (let [vShader (s/load-shader ShaderType/VertexShader s/vtex-src)
        fShader (s/load-shader ShaderType/FragmentShader s/frag-src)]
    
    (GL/AttachShader @handler vShader)
    (GL/AttachShader @handler fShader)

    (GL/LinkProgram @handler)

    (delete-shader vShader)
    (delete-shader fShader)

    (GL/UseProgram @handler)
    
    (GL/BindBuffer BufferTarget/ArrayBuffer @vbo)))
