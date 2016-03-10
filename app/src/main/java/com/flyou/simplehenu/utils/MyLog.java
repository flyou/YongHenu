package com.flyou.simplehenu.utils;

import android.util.Log;

import com.flyou.simplehenu.BuildConfig;


/**
 * ============================================================  
* 项目名称：androidTools   
* 
* 类名称：MyLog   
* 
* 类描述：   Log统一管理类
* 
* 创建人：flyou  
* 
* 创建时间：2015-3-31 下午12:12:41  
*  
* 修改备注：   
* 
* 版本：@version    
*   ============================================================
 */
public class MyLog {

  public static boolean isDebug = BuildConfig.LOG_DEBUG;// 是否需要打印bug，可以在application的onCreate函数里面初始化
  private static final String TAG = "flyou";

  // 下面四个是默认tag的函数
  public static void i(String msg) {
    if (isDebug)
      Log.i(TAG, msg);
  }

  public static void d(String msg) {
    if (isDebug)
      Log.d(TAG, msg);
  }

  public static void e(String msg) {
    if (isDebug)
      Log.e(TAG, msg);
  }

  public static void v(String msg) {
    if (isDebug)
      Log.v(TAG, msg);
  }

  // 下面是传入自定义tag的函数
  public static void i(String tag, String msg) {
    if (isDebug)
      Log.i(tag, msg);
  }

  public static void d(String tag, String msg) {
    if (isDebug)
      Log.i(tag, msg);
  }

  public static void e(String tag, String msg) {
    if (isDebug)
      Log.i(tag, msg);
  }

  public static void v(String tag, String msg) {
    if (isDebug)
      Log.i(tag, msg);
  }
}