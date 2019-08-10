(ns chromium.shaders
  (:import [OpenTk]
           [OpenTK.Graphics.ES30 PrimitiveType]
           [OpenTK.Graphics.OpenGL4 GL BufferTarget]))

(def vtex-src
  (slurp "shaders/vert.shader" :encoding "UTF-8"))

(def frag-src
  (slurp "shaders/frag.shader" :encoding "UTF-8"))


(defn bind-shader [shader shader-src]
  (GL/ShaderSource shader shader-src))

(defn compile-shader [shader]
  (GL/CompileShader shader))

(defn load-shader [type src]
  (let [shad (GL/CreateShader type)]
    (bind-shader shad src)
    (compile-shader shad)
    (if-let [log (not-empty (GL/GetShaderInfoLog shad))]
      (Console/WriteLine log))
    shad))
