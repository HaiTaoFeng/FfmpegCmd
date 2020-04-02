# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.4.1)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.


#set(distribution_DIR ${CMAKE_SOURCE_DIR}/libs)
# 需要引入我们头文件,以这个配置的目录为基准
include_directories(src/main/jniLibs/include)

set(distribution_DIR ${CMAKE_SOURCE_DIR}/src/main/jniLibs)

message("distribution_DIR:" ${distribution_DIR})

add_library( libavcodec
        SHARED
        IMPORTED )


add_library( libavdevice
        SHARED
        IMPORTED )


add_library( libavfilter
        SHARED
        IMPORTED )


add_library( libavformat
        SHARED
        IMPORTED )


add_library( libavutil
        SHARED
        IMPORTED )

add_library( libpostproc
        SHARED
        IMPORTED )


add_library( libswresample
        SHARED
        IMPORTED )

add_library( libswscale
        SHARED
        IMPORTED )


set_target_properties( libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libavcodec.so)



set_target_properties( libavdevice
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libavdevice.so)


set_target_properties( libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libavfilter.so)


set_target_properties( libavformat
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libavformat.so)


set_target_properties( libavutil
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libavutil.so)

set_target_properties( libpostproc
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libpostproc.so)


set_target_properties( libswresample
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libswresample.so)



set_target_properties( libswscale
        PROPERTIES IMPORTED_LOCATION
        ${distribution_DIR}/${ANDROID_ABI}/libswscale.so)

add_library( # Sets the name of the library.
        native-lib
        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ${CMAKE_SOURCE_DIR}/src/main/cpp/native-lib.cpp ${distribution_DIR}/include/cmdutils.c
        ${distribution_DIR}/include/ffmpeg.c ${distribution_DIR}/include/ffmpeg_filter.c
        ${distribution_DIR}/include/ffmpeg_hw.c ${distribution_DIR}/include/ffmpeg_opt.c)



# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.



find_library( # Sets the name of the path variable.
        log-lib
        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)


target_link_libraries( # Specifies the target library.
        native-lib
        libavcodec
        libavdevice
        libavfilter
        libavformat
        libavutil
        libpostproc
        libswresample
        libswscale
        android
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )