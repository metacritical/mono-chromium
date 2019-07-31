(ns chromium.window
  (:import [System]
           [OpenTK]
           [OpenTK.Graphics.ES30 ClearBufferMask]
           [OpenTK.Graphics.OpenGL GL]
           [OpenTK.Input Keyboard Key]))


(defn on-update-frame [this event]
  (let [input (Keyboard/GetState)]
    (if (.IsKeyDown input Key/Escape)
      (.Exit this))))

(defn set-bg-color [color]
  (GL/ClearColor color)
  (GL/Clear ClearBufferMask/ColorBufferBit))

(defn new [width height]
  (proxy [OpenTK.GameWindow] [width height nil "Mono Chromium BSU"]
    (OnUpdateFrame [event]
      (on-update-frame this event))

    (OnLoad [event]
      (comment "Do Something"))

    (OnResize [event]
      (comment "Do Something"))))

