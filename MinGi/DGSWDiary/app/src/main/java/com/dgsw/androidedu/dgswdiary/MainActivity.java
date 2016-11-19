package com.dgsw.androidedu.dgswdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private final static String TAG = "MainActivity";

    private Button btnIntro = null;
    private Button btnDiaryList = null;
    private Button btnDiaryView = null;
    private Button btnDiaryInsert = null;
    private Button btnDiaryEdit = null;
    private Button btnCategoryList = null;
    private Button btnCategoryDiaryList = null;
    private Button btnCategoryDiaryAdd = null;
    private Button btnCategoryDlg = null;
    private Button btnCategoryInsertDlg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIntro = (Button)this.findViewById(R.id.btnIntro);
        btnDiaryList = (Button)this.findViewById(R.id.btnDiaryList);
        btnDiaryView = (Button)this.findViewById(R.id.btnDiaryView);
        btnDiaryInsert = (Button)this.findViewById(R.id.btnDiaryInsert);
        btnDiaryEdit = (Button)this.findViewById(R.id.btnDiaryEdit);
        btnCategoryList = (Button)this.findViewById(R.id.btnCategoryList);
        btnCategoryDiaryList = (Button)this.findViewById(R.id.btnCategoryDiaryList);
        btnCategoryDiaryAdd = (Button)this.findViewById(R.id.btnCategoryDiaryAdd);
        btnCategoryDlg = (Button)this.findViewById(R.id.btnCategoryDlg);
        btnCategoryInsertDlg = (Button)this.findViewById(R.id.btnCategoryInsertDlg);

        btnIntro.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("TAG","start intro");

                Intent intent = new Intent(MainActivity.this, IntroActivity.class);

                MainActivity.this.startActivity(intent);
            }
        });

        btnDiaryList.setOnClickListener(view->{
            Log.i("TAG","start diary list");
            Intent intent = new Intent(this, DiaryListActivity.class);

            this.startActivity(intent);
        });

        btnDiaryView.setOnClickListener(btnDiaryViewHandler);

        btnDiaryEdit.setOnClickListener(this);
    }

    OnClickListener btnDiaryViewHandler = new OnClickListener(){
        @Override
        public void onClick(View v) {
            Log.i("TAG","start diary view");
        }
    };

    public void onDiaryInsert(View v)
    {
        Log.i("TAG","start diary insert");
    }

    public void onClick(View v) {
        Log.i("TAG","start diary edit");
    }


}
