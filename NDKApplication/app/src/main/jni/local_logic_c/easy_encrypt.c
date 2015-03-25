#include <string.h>
#include "easy_encrypt.h"
#include "./../utils/android_log_print.h"

/*
 * 功能：通过传入name生成加密后唯一的key值
 *
 * name 传入小于KEY_NAME_SIZE的字符串
 * return 通过name生成的验证key值
 */
char* generateKeyRAS(char* name)
{
    //判断形参是否有效
    if (NULL == name || strlen(name) > KEY_NAME_SIZE) {
        LOGD("function generateKey must have a ok name!\n");
        return NULL;
    }

    //声明局部变量
    int index = 0;
    int loop = 0;
    char temp[KEY_SIZE] = {"\0"};
    //清空数组内存
    memset(temp, 0, sizeof(temp));
    //将传进来的name拷贝到零时空间
    strcpy(temp, name);
    //进行通过name转化生成key的逻辑，这里是模拟测试，实际算法比这复杂
    for (index=0; index<KEY_SIZE-1; index++)
    {
        temp[index] = 93;
        LOGD("---------------temp[%d]=%c", index, temp[index]);
    }

    return temp;
}