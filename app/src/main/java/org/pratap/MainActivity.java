package org.pratap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import org.pratap.adapter.CardViewDataAdapter;
import org.pratap.interfaces.SelectCheckBox;
import org.pratap.models.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectCheckBox {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    public CardViewDataAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Student> studentList;

    private Button btnSelection;
    private Button select_all;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnSelection = (Button) findViewById(R.id.btn_show);
        select_all=(Button)findViewById(R.id.select_all);
        fab=(FloatingActionButton)findViewById(R.id.fab) ;

        studentList = new ArrayList<Student>();


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Android Students");

        }

        for (int i = 1; i <= 20; i++) {
            Student st = new Student("Student " + i, "androidstudent" + i
                    + "@gmail.com", false);

            studentList.add(st);
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new CardViewDataAdapter(studentList);
        mAdapter.setCallBack(MainActivity.this);
        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);


        select_all.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> stList = ((CardViewDataAdapter) mAdapter).getStudentist();
                for (int i = 0; i < stList.size(); i++) {
                    Student singleStudent = stList.get(i);
                    singleStudent.setName(singleStudent.getName());
                    singleStudent.setEmailId(singleStudent.getEmailId());
                    singleStudent.setSelected(true);
                }

                // create an Object for Adapter
                mAdapter = new CardViewDataAdapter(stList);

                // set the adapter object to the Recyclerview
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        fab.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<Student> stList = ((CardViewDataAdapter) mAdapter)
                        .getStudentist();

                for (int i = 0; i < stList.size(); i++) {
                    Student singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {

                        data = data + "\n" + singleStudent.getName().toString();

                    }

                }

                Toast.makeText(MainActivity.this,
                        "Selected Students: \n" + data, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    @Override
    public void selcetCheckBox( Student mModel) {
        String value="";
        List<Student> stList = ((CardViewDataAdapter) mAdapter)
                .getStudentist();
        for (int i = 0; i < stList.size(); i++) {
            Student singleStudent = stList.get(i);
            if (singleStudent.isSelected() == true) {
                value="true";
            }

        }

        if(value.equals("true")){
            fab.setVisibility(View.VISIBLE);
        }else {
            fab.setVisibility(View.GONE);
        }
    }
}
