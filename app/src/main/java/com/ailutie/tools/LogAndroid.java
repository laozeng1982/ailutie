package com.ailutie.tools;

/**
 * Created by deep on 7/31/17.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import android.util.Log;

import java.io.Serializable;

/**
 * for Android log trace
 * easy to find where output the log
 * @author JianGe
 */
public class LogAndroid implements Serializable {

    private static final long serialVersionUID = -3042686055658047285L;
    static StackTraceElement[] traces;
    static String TAG ="Debug" ;

    public static void v(String msg) {
        traces = new Throwable().getStackTrace();
        int printTraceLength = traces.length;

        for (int i = 0; i < printTraceLength; i++) {
            if (i <= printTraceLength - 2) {
                System.out.println(traces[i] + " is called by ->" + traces[i + 1]);
            } else {
                System.out.println(traces[i] + " : " + msg);
            }
        }
    }

    public static void d(String msg) {
        traces = new Throwable().getStackTrace();

        System.out.println(traces[1] + " : " + msg);

    }

    public static void d(Object object) {
        traces = new Throwable().getStackTrace();

        System.out.println(traces[1] + " : " + object.toString());

    }

    public static void e() {
        Log.e(TAG,traces[1] + "Program Just run to here!");
    }

    public static void e(Object object, boolean msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + object.getClass().toString() + " is: " + msg);
        Log.e(TAG,traces[1] + object.getClass().getDeclaredFields()[0].toString() + " is: " + msg);

    }

    public static void e(Object object, int msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + object.getClass().toString() + " is: " + msg);
//        Log.e(TAG,traces[1] + object. + " is: " + msg);

    }

    public static void e(boolean msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + " : the value is: " + msg);

    }

    public static void e(int msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + " : the value is: " + msg);
    }

    public static void e(long msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + " : the value is: " + msg);
    }

    public static void e(float msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + " : the value is: " + msg);
    }

    public static void e(double msg) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces[1] + " : the value is: " + msg);
    }

    public static void e(String msg) {
        traces = new Throwable().getStackTrace();
        Log.e(TAG, traces[1] + " : " + msg);
    }

    public static void e(Object object) {
        traces = new Throwable().getStackTrace();

        Log.e(TAG,traces + " : " + object.toString());
    }
}
