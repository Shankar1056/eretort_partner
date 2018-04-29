package apextechies.eretortpartner.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import apextechies.eretortpartner.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StuTeaMainActivity extends AppCompatActivity {

    @BindView(R.id.addteacer)
    TextView addteacer;
    @BindView(R.id.addstudent)
    TextView addstudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stutea_main);
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        ButterKnife.bind(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


@OnClick({R.id.addteacer, R.id.addstudent})
    void onClick(TextView textView){
        switch (textView.getId()){
            case R.id.addstudent:
                startActivity(new Intent(StuTeaMainActivity.this, AddStudent.class));
                break;

            case R.id.addteacer:
                startActivity(new Intent(StuTeaMainActivity.this, AddTeacher.class));
                break;
        }
}
}
