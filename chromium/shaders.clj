(ns chromium.shaders
  (:import [OpenTk]
           [OpenTK.Graphics.ES30 PrimitiveType]
           [OpenTK.Graphics.OpenGL4 GL BufferTarget]))

(def vtex-src
  (slurp "shaders/vert.shader" :encoding "UTF-8"))

(def frag-src
  (slurp "shaders/frag.shader" :encoding "UTF-8"))


(defn bindShader [shader shader-src]
  (GL/ShaderSource shader shader-src))

(defn compileShader [shader]
  (GL/CompileShader shader))

(defn load-shader [type src]
  (let [shad (GL/CreateShader type)]
    (bindShader shad src)
    (compileShader shad)
    (if-let [log (not-empty (GL/GetShaderInfoLog shad))]
      (Console/WriteLine log))
    shad))

(defn detachndelete [handle shader]
  (GL/DetachShader handle shader)
  (GL/DeleteShader shader))
