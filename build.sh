#!/usr/local/bin/bash

runtime="mono"
cljcomp=$CLOJURE_LOAD_PATH/Clojure.Compile.exe
BUILD_PATH=build/
MAIN_NS=chromium.core


compile(){
  CLOJURE_COMPILE_PATH=$BUILD_PATH $runtime $cljcomp $MAIN_NS
}

clean_up(){
  rm -rf build/*
}

link_dlls(){
  if [ -d "$BUILD_PATH" ]; then
    clean_up
    ln -s $CLOJURE_LOAD_PATH/*.dll build/
    cd build/
    ln -s ../extern/OpenTK/lib/net20/OpenTK.dll .
  else
    mkdir $BUILD_PATH
    link_dlls
  fi
}

run(){
  $runtime build/$MAIN_NS.exe
}

build(){
  rm -rf release
  mkdir release
  cp $CLOJURE_LOAD_PATH/*.dll release/
  cp build/*.exe release/
  echo "A build created in release directory"
}

release(){
  echo "Does nothing for now .. will build a minimal exe eventually"
  #TODO: mono extern/ILMerge.3.0.29/tools/net452/ILMerge.exe ILRepack or monolinker/mkbundle
}


case "$1" in
  c|compile)
    compile
    ;;
  l|link)
    link_dlls
    ;;
  r|run)
    run
    ;;
  b|build)
    build
    ;;
  z|rel)
    release
    ;;
  q|clean)
    clean_up
    ;;
  *)
    echo "Clojure CLR build script."
    echo "Usage: $0 [Options]"
    echo "Mandatory Options :
         c | compile
         l | link
         r | run
         b | build
         z | rel
         q | clean"
esac


