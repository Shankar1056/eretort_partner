package apextechies.eretortpartner.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import apextechies.eretortpartner.R;
import apextechies.eretortpartner.common.PreferenceName;
import apextechies.eretortpartner.common.Utilz;
import apextechies.eretortpartner.model.StuTeaModel;
import apextechies.eretortpartner.retrofit.DownlodableCallback;
import apextechies.eretortpartner.retrofit.RetrofitDataProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddStudent extends AppCompatActivity {

    @BindView(R.id.input_rollnumber)
    EditText input_rollnumber;
    @BindView(R.id.input_name)
    EditText input_name;
    @BindView(R.id.input_email)
    EditText input_email;
    @BindView(R.id.input_mobile)
    EditText input_mobile;
    @BindView(R.id.input_class)
    EditText input_class;
    @BindView(R.id.input_section)
    EditText input_section;
    @BindView(R.id.input_admission)
    EditText input_admission;
    @BindView(R.id.input_address)
    EditText input_address;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RetrofitDataProvider retrofitDataProvider;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);
      //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        ButterKnife.bind(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        retrofitDataProvider = new RetrofitDataProvider(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.add_student));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_rollnumber.getText().toString().trim().equals("")){
                    Toast.makeText(AddStudent.this, "Enter Rollnumber Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (input_name.getText().toString().trim().equals("")){
                    Toast.makeText(AddStudent.this, "Enter Name Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (input_class.getText().toString().trim().equals("")){
                    Toast.makeText(AddStudent.this, "Enter Class Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (input_address.getText().toString().trim().equals("")){
                    Toast.makeText(AddStudent.this, "Enter Address Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if (Utilz.isInternetConnected(AddStudent.this)) {
                        submitData();
                    }
                    else {
                        Toast.makeText(AddStudent.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        input_admission.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddStudent.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        input_admission.setText(sdf.format(myCalendar.getTime()));
    }
    private void submitData() {
        Utilz.showDailog(AddStudent.this, getResources().getString(R.string.pleasewait));
        String rollNumber = input_rollnumber.getText().toString().trim();
        String name = input_name.getText().toString().trim();
        String email = input_email.getText().toString().trim();
        String mobile = input_mobile.getText().toString().trim();
        String clas = input_class.getText().toString().trim();
        String sec = input_section.getText().toString().trim();
        String admission = input_admission.getText().toString().trim();
        String address = input_address.getText().toString().trim();
        retrofitDataProvider.addstudent(rollNumber, name, email, mobile, clas, sec, admission, address, new DownlodableCallback<StuTeaModel>() {
            @Override
            public void onSuccess(final StuTeaModel result) {
                //  closeDialog();

                Utilz.closeDialog();

                if (result.getStatus().contains(PreferenceName.TRUE)) {

                    Toast.makeText(AddStudent.this, ""+result.getData(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(AddStudent.this, ""+result.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(String error) {
                // closeDialog();
            }

            @Override
            public void onUnauthorized(int errorNumber) {

            }
        });
    }
}