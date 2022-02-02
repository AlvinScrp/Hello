
cmake_minimum_required(VERSION 3.4.1)
project("canvas_render")
include_directories("${CMAKE_SOURCE_DIR}")

link_directories(${PROJECT_SOURCE_DIR}/../../../libs/armeabi-v7a)
#include_directories(${CMAKE_SOURCE_DIR})

aux_source_directory(${CMAKE_SOURCE_DIR} SRC_LIST)

add_library(skia SHARED IMPORTED)

set_target_properties(skia PROPERTIES IMPORTED_LOCATION
        "${PROJECT_SOURCE_DIR}/../../../libs/${ANDROID_ABI}/libskia.so")


message("-------------------------")
message("CMAKE_SOURCE_DIR:${CMAKE_SOURCE_DIR}")
message("file src:${SRC_LIST}")

add_library(render
        SHARED
        "${SRC_LIST}")

find_library(opensl OpenSLES)

find_library(
        log-lib

        log)


target_include_directories(render PUBLIC
        "${CMAKE_SOURCE_DIR}"
        "${CMAKE_SOURCE_DIR}/include"
        "${CMAKE_SOURCE_DIR}/include/android"
        "${CMAKE_SOURCE_DIR}/include/atlastext"
        "${CMAKE_SOURCE_DIR}/include/c"
        "${CMAKE_SOURCE_DIR}/include/codec"
        "${CMAKE_SOURCE_DIR}/include/config"
        "${CMAKE_SOURCE_DIR}/include/core"
        "${CMAKE_SOURCE_DIR}/include/effects"
        "${CMAKE_SOURCE_DIR}/include/pathops"
        "${CMAKE_SOURCE_DIR}/include/ports"
        "${CMAKE_SOURCE_DIR}/include/encode"
        "${CMAKE_SOURCE_DIR}/include/gpu"
        "${CMAKE_SOURCE_DIR}/include/private"
        "${CMAKE_SOURCE_DIR}/include/svg"
        "${CMAKE_SOURCE_DIR}/include/ports"
        "${CMAKE_SOURCE_DIR}/include/third_party"
        "${CMAKE_SOURCE_DIR}/include/utils")

target_link_libraries(render

        skia
        -ljnigraphics
        -landroid
        ${opensl}
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})