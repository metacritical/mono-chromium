(ns chromium.core
  (:gen-class)
  (:require [chromium.load-assembly]
            [chromium.window :as window])
  (:import [System]
           [System.IO]
           [System.Console]))

(defn -main []
  (Console/WriteLine "Starting OpenTK Window.")
  (let [game (window/new 800 600)]
    (.Run game 30.0)))


