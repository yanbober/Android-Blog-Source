#include <jni.h>
#include <string.h>
#include "io_github_yanbober_ndkapplication_NdkJniUtils.h"
#include "./utils/android_log_print.h"
#include "./local_logic_c/easy_encrypt.h"

JNIEXPORT jstring JNICALL Java_io_github_yanbober_ndkapplication_NdkJniUtils_generateKey
  (JNIEnv *env, jobject obj, jstring name){
     //声明局部量
     char key[KEY_SIZE] = {0};
     memset(key, 0, sizeof(key));

     char temp[KEY_NAME_SIZE] = {0};

     //将java传入的name转换为本地utf的char*
     const char* pName = (*env)->GetStringUTFChars(env, name, NULL);

     if (NULL != pName) {
        strcpy(temp, pName);
        strcpy(key, generateKeyRAS(temp));

        //java的name对象不需要再使用，通知虚拟机回收name
        (*env)->ReleaseStringUTFChars(env, name, pName);
     }

     return (*env)->NewStringUTF(env, key);
  }