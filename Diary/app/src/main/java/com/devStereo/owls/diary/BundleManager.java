package com.devStereo.owls.diary;

import android.os.Bundle;

/**
 * Created by Cloud on 2017-03-12.
 */

public class BundleManager{
    String  bundleKey="mybundle";
    public  Bundle myBundle;
    int tid;
    String myTopic;
    String  myContents;

    BundleManager(){
        myBundle=new Bundle(3);

    }

    public void setBundle(int tid, String myTopic, String myContents){

        myBundle.putInt("tid",tid);
        myBundle.putString("myTopic",myTopic);
        myBundle.putString("myContents",myContents);
    }

    public Bundle getBundle(){
        return myBundle;
    }

}
