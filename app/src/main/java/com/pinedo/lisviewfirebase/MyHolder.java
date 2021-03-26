package com.pinedo.lisviewfirebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by pinedo on 4/2/18.
 */

public class MyHolder {

    TextView nameTxt;
    ImageView img;
    public MyHolder(View itemView) {


        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        img=(ImageView) itemView.findViewById(R.id.dogimage);


    }
}
