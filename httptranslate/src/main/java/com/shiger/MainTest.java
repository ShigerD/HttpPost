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

        UserTest userTest1 = new UserTest().setName("a").setNumber("a");
        UserTest userTest2 = new UserTest().setName("a").setNumber("a");
        boolean b = userTest1.equals(userTest2);

        Utils.logD(TAG, "b >>" + b);

    }


    public static class UserTest{
        String name;
        String number;

        public String getName() {
            return name;
        }

        public UserTest setName(String name) {
            this.name = name;
            return this;
        }

        public String getNumber() {
            return number;
        }

        public UserTest setNumber(String number) {
            this.number = number;
            return this;
        }
    }


}
