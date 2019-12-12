//
// Created by Administrator on 2018/9/7 0007.
//

#include <jni.h>

#ifndef FIREFOX_SERIALPORT_H
#define FIREFOX_SERIALPORT_H

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     android_serialport_api_SerialPort
 * Method:    open
 * Signature: (Ljava/lang/String;II)Ljava/io/FileDescriptor;
 */
JNIEXPORT jobject JNICALL Java_com_zhangxin_serialport_api_SerialPort_open
        (JNIEnv *, jclass, jstring, jint, jint);

/*
 * Class:     android_serialport_api_SerialPort
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_zhangxin_serialport_api_SerialPort_close
        (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //FIREFOX_SERIALPORT_H
