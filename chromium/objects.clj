(ns chromium.objects)

(def triangle
  (float-array [-0.5 0.0 0.0 0.5 0.0 0.0 0.5 0.5 0.0]))

(def size-of-tri
  (* (Marshal/SizeOf (float 0)) (count triangle)))
