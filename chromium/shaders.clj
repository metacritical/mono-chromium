(ns chromium.shaders
  (:import [OpenTk]
           [OpenTK.Graphics.ES30 PrimitiveType]
           [OpenTK.Graphics.OpenGL4 GL BufferTarget]))

(defonce vtex-src
  "#version 330 core

layout(location = 0) in vec3 aPosition;

void main(void)
{
    gl_Position = vec4(aPosition, 1.0);
}")

(defonce frag-src
  "#version 330

out vec4 outputColor;

void main()
{
    outputColor = vec4(1.0, 1.0, 0.0, 1.0);
}")


(defn bindShader [shader shader-src]
  (GL/ShaderSource shader shader-src))

(defn compileShader [shader]
  (GL/CompileShader shader))

(defn load-shader [type src]
  (let [shad (GL/CreateShader type)]
    (bindShader shad src)
    (compileShader shad)
    shad))

(defn detachndelete [handle shader]
  (GL/DetachShader handle shader)
  (GL/DeleteShader shader))
