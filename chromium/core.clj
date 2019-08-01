(ns chromium.core
  (:gen-class)
  (:require [chromium.load-assembly]
            [chromium.window :as window])
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


