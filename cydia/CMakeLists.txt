# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

cmake_minimum_required(VERSION 3.10.2)
project("hello")

add_library(hellohook.cy SHARED src/main/cpp/hello-hook.cy.cpp)
target_include_directories(hellohook.cy PRIVATE  ${CMAKE_SOURCE_DIR}/src/main/cpp/include)
find_library(log-lib log)
file(GLOB libs ${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi-v7a/libsubstrate.so ${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi-v7a/libsubstrate-dvm.so)
target_link_libraries( hellohook.cy   ${libs}  ${log-lib})



add_library(dexhook.cy SHARED src/main/cpp/dex-hook.cy.cpp)
target_include_directories(dexhook.cy PRIVATE  ${CMAKE_SOURCE_DIR}/src/main/cpp/include)
#find_library(log-lib log)
#file(GLOB libs ${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi-v7a/libsubstrate.so ${CMAKE_SOURCE_DIR}/src/main/jniLibs/armeabi-v7a/libsubstrate-dvm.so)
target_link_libraries( dexhook.cy   ${libs}  ${log-lib})


