#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_blk_blkpangkep_Network_RetrofitClient_BASEURL(
        JNIEnv* env,
        jobject /* this */) {

    return (*env).NewStringUTF("https://api.rss2json.com/");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_blk_blkpangkep_Network_RetrofitClientTwo_BASEURLTWO(JNIEnv *env, jobject thiz) {
    // TODO: implement BASEURLTWO()
    return (*env).NewStringUTF("https://raw.githubusercontent.com/MrWildanMD/pesertajsonexample/");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_blk_blkpangkep_Network_RetrofitClientThree_BASEURLTHREE(JNIEnv *env, jobject thiz) {
    // TODO: implement BASEURLTHREE()
    return (*env).NewStringUTF("https://youtube.googleapis.com/youtube/v3/");
}
