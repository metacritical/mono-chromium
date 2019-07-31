(assembly-load-from "./extern/OpenTK/lib/net20/OpenTK.dll")

(ns chromium.core
  (:gen-class)
  (:require [chromium.window :as window])
  (:import [System]
           [System.IO]
           [System.Console]
           [System.Drawing]))

(defn -main []
  (Console/WriteLine "Starting OpenTK Window.")
  (let [game (window/new 800 600)]
    (window/set-bg-color Color/CornflowerBlue)
    (.SwapBuffers game)
    (.Run game 30.0)))


