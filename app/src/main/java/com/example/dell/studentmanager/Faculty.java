package com.example.dell.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dell.studentmanager.LetterImageView;

public class Faculty extends AppCompatActivity {


    private Toolbar toolbar;
    private ListView subjectsList;
    String [] subjects;
    String [] teacher;
    TextView Name,subject,yearsOfExperience,qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        toolbar = findViewById(R.id.toolbarfac);
        subjectsList = (ListView) findViewById(R.id.facultyList);
//        Name = findViewById(R.id.name);
//        subject = findViewById(R.id.subjectstaught);
//        yearsOfExperience = findViewById(R.id.years);
//        qualification = findViewById(R.id.qualification);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("STUDENT MANAGER");

        Resources res = getResources();
        subjects = res.getStringArray(R.array.subjects);
        teacher = res.getStringArray(R.array.teacher);

        FacultyAdapter adapter = new FacultyAdapter(this,subjects,teacher);
        subjectsList.setAdapter(adapter);
        // FacultyDesc a = new FacultyDesc();

//        subjectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(Faculty.this,FacultyDesc.class);
//                startActivity(intent);
//
//                switch (i){
//
//                    case 0:
//                        Toast.makeText(Faculty.this,"spcc",Toast.LENGTH_SHORT).show();
//
//                        Intent spcc = new Intent(Faculty.this,FacultyDesc.class);
//                        //spcc.putExtra("spcc",0);
//                        spcc.putExtra("spcc",i);
//                        startActivity(spcc);
////                        Name.setText("Name: Ms. Snehal Mumbaikar");
////                        subject.setText("Subject Taught: SPCC");
////                        yearsOfExperience.setText("Qualification: M.E");
////                        qualification.setText("Years of Experience: 5 years");
////                        Toast.makeText(Faculty.this," "+spcc,Toast.LENGTH_SHORT).show();
//
//                        break;
//
//                    case 1:
//                        Toast.makeText(Faculty.this,"mcc",Toast.LENGTH_SHORT).show();
//                        Intent mcc = new Intent(Faculty.this,FacultyDesc.class);
//                        //mcc.putExtra("mcc","1");
//                        mcc.putExtra("mcc",i);
//                        startActivity(mcc);
//                        //Name.setText("Name: Mr. Vishwanath Chikareddi");
//                        //subject.setText("Subject Taught: MCC");
//                        //yearsOfExperience.setText("Qualification: M.E");
//                        //qualification.setText("Years of Experience: 7 years");
//                        // Toast.makeText(Faculty.this," "+mcc,Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 2:
//                        Toast.makeText(Faculty.this,"ddb",Toast.LENGTH_SHORT).show();
//                        Intent ddb = new Intent(Faculty.this,FacultyDesc.class);
//                        // ddb.putExtra("ddb","2");
//                        ddb.putExtra("ddb",i);
//                        startActivity(ddb);
////                        Name.setText("Name: Ms. Rohini Damhane");
////                        subject.setText("Subject Taught: DDB");
////                        yearsOfExperience.setText("Qualification: B.E");
////                        qualification.setText("Years of Experience: 2 years");
//                        //Toast.makeText(Faculty.this," "+ddb,Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 3:
//                        Toast.makeText(Faculty.this,"se",Toast.LENGTH_SHORT).show();
//                        Intent se = new Intent(Faculty.this,FacultyDesc.class);
//                        //se.putExtra("se",3);
//                        se.putExtra("se",i);
//                        startActivity(se);
////                        Name.setText("Name: Dr. Bharti Joshi");
////                        subject.setText("Subject Taught: SE");
////                        yearsOfExperience.setText("Qualification: PhD");
////                        qualification.setText("Years of Experience: 12 years");
//                        // Toast.makeText(this," "+se,Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 4:
//                        Toast.makeText(Faculty.this,"pm",Toast.LENGTH_SHORT).show();
//                        Intent pm = new Intent(Faculty.this,FacultyDesc.class);
//                        //pm.putExtra("pm",4);
//                        pm.putExtra("pm",i);
//                        startActivity(pm);
////                        Name.setText("Name: Ms. Payal Sancheti");
////                        subject.setText("Subject Taught: PM");
////                        yearsOfExperience.setText("Qualification: B.E");
////                        qualification.setText("Years of Experience: 1 years");
//                        // Toast.makeText(this," "+pm,Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 5:
//                        Toast.makeText(Faculty.this,"npl",Toast.LENGTH_SHORT).show();
//                        Intent npl = new Intent(Faculty.this,FacultyDesc.class);
//                        //npl.putExtra("npl",5);
//                        npl.putExtra("npl",i);
//                        startActivity(npl);
////                        Name.setText("Name: Ms. Siddhi Patni");
////                        subject.setText("Subject Taught: NPL");
////                        yearsOfExperience.setText("Qualification: B.E");
////                        qualification.setText("Years of Experience: 2 years");
//                        //Toast.makeText(this," "+npl,Toast.LENGTH_SHORT).show();
//                        break;
//
//                    //default:break;
//                }
//            }
//        });
    }


    public class FacultyAdapter extends ArrayAdapter {

        Context context;
        String sub[],fac[];

        public FacultyAdapter(@NonNull Context context, String [] subjects, String [] teacher) {
            super(context,R.layout.single_row,R.id.subjectText,subjects);
            this.context = context;
            this.sub = subjects;
            this.fac = teacher;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row,parent,false);
            TextView subjectText = row.findViewById(R.id.subjectText);
            TextView subjectTeacher = row.findViewById(R.id.subjectTeacher);
            LetterImageView letterImageView = (LetterImageView) row.findViewById(R.id.letter);
            letterImageView.setLetter(sub[position].charAt(0));
            letterImageView.setOval(true);
            subjectText.setText(sub[position]);
            subjectTeacher.setText(fac[position]);
            return row;
        }
    }

}

/*
*
* <string-array name="teacher">
        <item>Ms. Snehal Mumbaikar</item>
        <item> Mr. Vishwanath Chikareddi</item>
        <item>Ms. Rohini Damhane</item>
        <item>Dr. Bharti Joshi</item>
        <item>Ms. Payal Sancheti</item>
		<item>Ms. Siddhi Patni</item>
    </string-array>
*
*
* */


