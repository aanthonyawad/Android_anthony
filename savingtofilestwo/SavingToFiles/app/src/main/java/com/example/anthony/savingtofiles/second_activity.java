package com.example.anthony.savingtofiles;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileOutputStream;

/**
 * Created by anthony on 6/21/17.
 */

public class second_activity extends AppCompatActivity {
ListView l;
    TextView t;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        l = (ListView) findViewById(R.id.lv);
        t = (TextView)findViewById(R.id.tv);

        // ta jib String mnel countries bel string xml
        String [] t1 =new String[6];
        for(int i=0;i<t1.length;i++){
            t1[i]=""+getResources().getTextArray(R.array.countries)[i];
        }
        // table tabaa images
        int [] img = {R.drawable.lebanon,R.drawable.russia,R.drawable.syria,R.drawable.jordan,R.drawable.usa,R.drawable.serbia};



        // am esta3mel constructor li khala2to bel class taht
        // w b3ammer class taba3e
        l.setAdapter(new dataListAdapter(t1,img));


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                t.setText(getResources().getTextArray(R.array.countries)[position]);
                String string = ""+getResources().getTextArray(R.array.countries)[position];
                String filename = "f3";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    //Class tabaa my custom list view
    public class dataListAdapter extends BaseAdapter {

        // variable li fiya li bede a3melon show atoul table of variables

        String[] Title;
        int[] imge;



        //constructor
        public dataListAdapter(String[] text,int[] text3) {
            Title = text;

            imge = text3;

        }
// haw 3 method ejbare ykuno (@override)
        public int getCount() {
            // TODO Auto-generated method stub
            return Title.length;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }


        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

                //method ta 3abbe kel row b row tab3a


            LayoutInflater inflater = getLayoutInflater();
            View row;
            // hone 3am rakkeb layout listitem.xml juwa layout parent yalle huwe act2.xml la22ano ayatet lal constructor fiya
            row = inflater.inflate(R.layout.listitem, parent, false);
            TextView title;
            ImageView i1;
            title = (TextView) row.findViewById(R.id.text);

            i1=(ImageView)row.findViewById(R.id.iv);
            title.setText(Title[position]);

            i1.setImageResource(imge[position]);

            return (row);
        }


    }



}
