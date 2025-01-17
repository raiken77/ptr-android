# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#Firebase and Glide rules
-keepattributes *Annotation*, Signature, Exception
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# App data models
-keepclassmembers class com.mru.ptr.district.ui.model.** {*;}
-keepclassmembers class com.mru.ptr.event.ui.model.** {*;}
-keepclassmembers class com.mru.ptr.event.ui.model.** {*;}
-keepclassmembers class com.mru.ptr.gallery.ui.VideoDataModel {*;}
-keepclassmembers class com.mru.ptr.gallery.ui.PhotoDataModel {*;}
-keepclassmembers class com.mru.ptr.manifesto.ui.model.** {*;}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}





