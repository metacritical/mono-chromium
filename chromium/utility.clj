(ns chromium.utility
  (:import [OpenTK.Graphics.OpenGL4 GL]
           [OpenTK.Graphics.ES30 BufferUsageHint]
           [OpenTK.Graphics.ES30 ClearBufferMask]))

(defn opengl-version []
  (str "OpenGL Version: " (GL/GetString StringName/Version)))

(defn clear-buffer-mask []
  (GL/Clear ClearBufferMask/ColorBufferBit))

(defn set-bg-color
  ([color]
   (GL/ClearColor color))

  ([r g b a]
   (GL/ClearColor r g b a)))

(defn ptr-to [obj]
  (let [pinned-obj
        (GCHandle/Alloc obj GCHandleType/Pinned)]
    (try 
      (.AddrOfPinnedObject pinned-obj)
      (finally
        (if (.IsAllocated pinned-obj) (.Free pinned-obj))))))





