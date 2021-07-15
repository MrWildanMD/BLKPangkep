#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_blk_blkpangkep_Network_RetrofitClient_BASEURL(
        JNIEnv* env,
        jobject /* this */) {

    return (*env).NewStringUTF("https://api.rss2json.com/");
}