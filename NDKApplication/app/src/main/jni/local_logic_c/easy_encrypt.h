#ifndef _EASY_ENCRYPT_H_
#define _EASY_ENCRYPT_H_
/*
 * 作者：晏博
 *
 * 功能：通过name获取加密后的key
 * 类型：测试代码
 */
#define KEY_NAME_SIZE  (6)
#define KEY_SIZE  (129)

char* generateKeyRAS(char* name);

#endif /* _EASY_ENCRYPT_H_ */