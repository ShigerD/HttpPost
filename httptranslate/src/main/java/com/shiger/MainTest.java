package com.shiger;

import com.shiger.utils.Utils;

import java.io.IOException;

/**
 * created by shiger on 8/8/18.
 */

public class MainTest {

    public static String TAG = Thread.currentThread().getStackTrace()[1].getFileName();

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        String getStackTrace = Thread.currentThread().getStackTrace()[1].getFileName();
        Utils.logD(TAG, getStackTrace);

    }



}
