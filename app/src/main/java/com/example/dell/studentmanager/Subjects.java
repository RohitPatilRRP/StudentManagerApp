package com.example.dell.studentmanager;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.dell.studentmanager.LetterImageView;


public class Subjects extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView subjectsList;
    String [] subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        toolbar = findViewById(R.id.toolbarsub);
        subjectsList = (ListView) findViewById(R.id.subjectsList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("STUDENT MANAGER");

        Resources res = getResources();
        subjects = res.getStringArray(R.array.subjects);

        SubjectAdapter adapter = new SubjectAdapter(this,R.layout.single_row,subjects);
        subjectsList.setAdapter(adapter);
    }


    public class SubjectAdapter extends ArrayAdapter{

        Context context;
        String sub[];
        LayoutInflater layoutInflater;
        private int resource;

        public SubjectAdapter(@NonNull Context context, int resource, @NonNull String[] subjects) {
            super(context, resource, subjects);
            this.context = context;
            this.resource = resource;
            this.sub = subjects;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View view = convertView;
            ViewHolder holder;
            if (view == null){
                holder = new ViewHolder();
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.single_row,parent,false);
                holder.letterImageView = (LetterImageView) view.findViewById(R.id.letter);
                holder.subjectName = view.findViewById(R.id.subjectText);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            holder.letterImageView.setOval(true);
            holder.letterImageView.setLetter(sub[position].charAt(0));
            holder.subjectName.setText(sub[position]);

            return view;
        }
    }

    class ViewHolder{

        LetterImageView letterImageView;
        TextView subjectName;
    }
}