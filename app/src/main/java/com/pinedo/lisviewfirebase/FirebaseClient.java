package com.pinedo.lisviewfirebase;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by pinedo on 4/2/18.
 */

public class FirebaseClient {

    Context c;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<Dog> dogies= new ArrayList<>();
    CustomAdapter customAdapter;


    public  FirebaseClient(Context c, String DB_URL, ListView listView){
        this.c= c;
        this.DB_URL= DB_URL;
        this.listView= listView;


        Firebase.setAndroidContext(c);
        firebase=new Firebase(DB_URL);
    }

    public  void savedata(String name, String url){
        Dog d= new Dog();
        d.setName(name);
        d.setUrl(url);

        firebase.child("dog").push().setValue(d);
    }

    public  void refreshdata(){
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getupdates(DataSnapshot dataSnapshot){

        dogies.clear();

        for(DataSnapshot ds :dataSnapshot.getChildren()){
            Dog d= new Dog();
            d.setName(ds.getValue(Dog.class).getName());
            d.setUrl(ds.getValue(Dog.class).getUrl());
            dogies.add(d);

        }
        if(dogies.size()>0)
        {
            customAdapter=new CustomAdapter(c, dogies);
            listView.setAdapter((ListAdapter) customAdapter);
        }else
        {
            Toast.makeText(c, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}
