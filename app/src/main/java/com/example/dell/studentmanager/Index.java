package com.example.dell.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Index extends AppCompatActivity {

    public android.support.v7.widget.Toolbar toolbar;
    public ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        toolbar = findViewById(R.id.toolbarmain);
        listView = (ListView) findViewById(R.id.lvmain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("STUDENT MANAGER");
        setuplistview();

    }
//
//    private void setupUIViews(){
//        toolbar = findViewById(R.id.toolbarmain);
//

    private void setuplistview() {

        String[] title1 = getResources().getStringArray(R.array.main);
        String[] desription = getResources().getStringArray(R.array.desc);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title1, desription);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new setOnItemClickListener());
    }


    public class SimpleAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        Context mcontext;
        TextView title, description;
        public String[] titlearray, descarray;
        ImageView imageView;

        public SimpleAdapter(SimpleAdapter simpleAdapter, String[] title1) {
        }

        public SimpleAdapter(SimpleAdapter simpleAdapter, String[] title1, String[] desription) {
        }


        private void setuplistview() {

            String[] title1 = getResources().getStringArray(R.array.main);
            String[] desription = getResources().getStringArray(R.array.desc);

            SimpleAdapter simpleAdapter = new SimpleAdapter(this, title1, desription);
            listView.setAdapter(simpleAdapter);
        }

        public SimpleAdapter(Context context, String[] title, String[] description) {

            mcontext = context;
            titlearray = title;
            descarray = description;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titlearray.length;
        }

        @Override
        public Object getItem(int i) {
            return titlearray[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = layoutInflater.inflate(R.layout.main_act_single_item, null);
            }
            title = (TextView) view.findViewById(R.id.tvmain);
            description = (TextView) view.findViewById(R.id.tvdesc);
            imageView = (ImageView) view.findViewById(R.id.ivmain);

            title.setText(titlearray[i]);
            description.setText(descarray[i]);
            if (titlearray[i].equalsIgnoreCase("Timetable")) {
                imageView.setImageResource(R.drawable.timetable);
            } else if (titlearray[i].equalsIgnoreCase("subjects")) {
                imageView.setImageResource(R.drawable.books);
            } else if (titlearray[i].equalsIgnoreCase("Faculty")) {
                imageView.setImageResource(R.drawable.faculty);
            } else {
                imageView.setImageResource(R.drawable.resource);
            }


            return view;
        }


    }

    private class setOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            switch (i){
                case 0:{
                    Intent intent = new Intent(Index.this,ZoomInZoomOut.class);
                    startActivity(intent);
                    break;
                }
                case 1:{
                    Intent intent = new Intent(Index.this,Subjects.class);
                    startActivity(intent);
                    break;
                }
                case 2:{
                    Intent intent = new Intent(Index.this,Faculty.class);
                    startActivity(intent);
                    break;
                }
                case 3:{
                    Intent intent = new Intent(Index.this,Resource.class);
                    startActivity(intent);
                    break;
                }
            }

        }
    }

}




//zoomageView = findViewById(R.id.myZoomageView);