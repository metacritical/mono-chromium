(ns chromium.window
  (:import [System]
           [OpenTK]
           [System.Drawing]
           [OpenTK.Graphics.ES30 ClearBufferMask]
           [OpenTK.Graphics.OpenGL GL BufferTarget]
           [OpenTK.Input Keyboard Key]))

(declare on-load on-update-frame on-resize on-render-frame on-unload)

(defn clear-buffer-mask []
  (GL/Clear ClearBufferMask/ColorBufferBit))

(defn set-bg-color
  ([color]
   (GL/ClearColor color))

  ([r g b a]
   (GL/ClearColor r g b a)))

(defn new [width height]
  (proxy [OpenTK.GameWindow] [width height nil "Mono Chromium BSU"]
    (OnUpdateFrame [event]
      (on-update-frame this event))

    (OnLoad [event]
      (on-load this event))

    (OnResize [event]
      (GL/Viewport 0 0 (.Width this) (.Height this)))

    (OnRenderFrame [event]
      (on-render-frame this event))

    (OnUnload [event]
      (on-unload this event))))

(defn on-load [this event]
  (set-bg-color 0.2 0.3 0.6 1.0))

(defn on-update-frame [this event]
  (let [input (Keyboard/GetState)]
    (when (.IsKeyDown input Key/Escape)
      (.Exit this))))

(defn on-render-frame [this event]
  (clear-buffer-mask)
  (.SwapBuffers this))

(defn on-unload [this event]
  (let [vertbuffobj (GL/GenBuffer)]
    (GL/BindBuffer BufferTarget/ArrayBuffer (int 0))
    (GL/DeleteBuffer vertbuffobj)))
