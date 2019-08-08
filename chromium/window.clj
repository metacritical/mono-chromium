(ns chromium.window
  (:require [chromium.canvas :as canvas])
  (:import [System]
           [OpenTK]
           [System.Drawing]
           [OpenTK.Graphics.ES30 ClearBufferMask]
           [OpenTK.Graphics.OpenGL4 GL BufferTarget]
           [OpenTK.Input Keyboard Key]))


(defn new [width height]
  (proxy [OpenTK.GameWindow]
      [width height GraphicsMode/Default "Mono Chromium BSU" 
       GameWindowFlags/Default
       DisplayDevice/Default 4 0
       GraphicsContextFlags/ForwardCompatible]

    (OnUpdateFrame [event]
      (canvas/on-update-frame this event)
      (proxy-super OnUpdateFrame event))

    (OnLoad [event]
      (canvas/on-load this event)
      (proxy-super OnLoad event))

    (OnResize [event]
      (GL/Viewport 0 0 (.Width this) (.Height this)))

    (OnRenderFrame [event]
      (canvas/on-render-frame this event)
      (proxy-super OnRenderFrame event))

    (OnUnload [event]
      (canvas/on-unload this event))))
