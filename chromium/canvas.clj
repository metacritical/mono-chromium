(ns chromium.canvas
  (:require [chromium.utility :as util]
            [chromium.objects :as obj]
            [chromium.onload :as onload])
  (:import [System.Drawing]
           [OpenTk]
           [OpenTK.Graphics.ES30 PrimitiveType]
           [OpenTK.Graphics.OpenGL4 GL BufferTarget]
           [OpenTK.Input Keyboard Key]))


(defn on-load [this event]
  (Console/WriteLine (util/opengl-version))
  (util/set-bg-color Color/CornflowerBlue)
  (onload/init)
  (onload/init-vbo)
  (onload/init-vao)
  (onload/render))

(defn on-update-frame [this event]
  (let [input (Keyboard/GetState)]
    (when (.IsKeyDown input Key/Escape)
      (.Exit this))))

(defn on-render-frame [this event]
  (util/clear-buffer-mask)
  (GL/UseProgram  @onload/handler)
  (GL/BindVertexArray @onload/vao)
  (GL/DrawArrays PrimitiveType/Triangles 0 3)
  (.SwapBuffers this))

(defn on-unload [this event]
  (GL/BindBuffer BufferTarget/ArrayBuffer (int 0))
  (GL/DeleteProgram @onload/handler))
