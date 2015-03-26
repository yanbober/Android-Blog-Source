#include <jni.h>
#include <string.h>
#include <assert.h>
#include "io_github_yanbober_ndkapplication_NdkJniUtils.h"
#include "./utils/android_log_print.h"
#include "./local_logic_c/easy_encrypt.h"

JNIEXPORT jstring JNICALL native_generate_key(JNIEnv *env, jobject obj, jstring name)
{
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

//参数映射表
static JNINativeMethod methods[] = {
    {"nativeGenerateKey", "(Ljava/lang/String;)Ljava/lang/String;", (void*)native_generate_key},
    //这里可以有很多其他映射函数
};

//自定义函数，为某一个类注册本地方法，调运JNI注册方法
static int registerNativeMethods(JNIEnv* env , const char* className , JNINativeMethod* gMethods, int numMethods)
{
    jclass clazz;
    clazz = (*env)->FindClass(env, className);
    if (clazz == NULL)
    {
        return JNI_FALSE;
    }
    //JNI函数，参见系列教程2
    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0)
    {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

//自定义函数
static int registerNatives(JNIEnv* env)
{
    const char* kClassName = "io/github/yanbober/ndkapplication/NdkJniUtils";//指定要注册的类
    return registerNativeMethods(env, kClassName, methods,  sizeof(methods) / sizeof(methods[0]));
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
{
    LOGD("customer---------------------------JNI_OnLoad-----into.\n");
    JNIEnv* env = NULL;
    jint result = -1;

    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4) != JNI_OK)
    {
        return -1;
    }
    assert(env != NULL);

    //动态注册，自定义函数
    if (!registerNatives(env))
    {
        return -1;
    }

    return JNI_VERSION_1_4;
}