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
import java.util.Random;

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

public class AddTeacher extends AppCompatActivity {

    @BindView(R.id.input_name)
    EditText input_name;
    @BindView(R.id.input_qualification)
    EditText input_qualification;
    @BindView(R.id.input_mobile)
    EditText input_mobile;
    @BindView(R.id.input_alternatenumber)
    EditText input_alternatenumber;
    @BindView(R.id.input_emailid)
    EditText input_emailid;
    @BindView(R.id.input_classtea)
    EditText input_classtea;
    @BindView(R.id.input_joindate)
    EditText input_joindate;
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
        setContentView(R.layout.activity_addtea);
       // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

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
        getSupportActionBar().setTitle(getResources().getString(R.string.add_teacer));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_name.getText().toString().trim().equals("")){
                    Toast.makeText(AddTeacher.this, "Enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Utilz.isInternetConnected(AddTeacher.this)) {
                    submitData();
                }
                else {
                    Toast.makeText(AddTeacher.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
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
        input_joindate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddTeacher.this, date, myCalendar
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

        input_joindate.setText(sdf.format(myCalendar.getTime()));
    }

    private void submitData() {
        Utilz.showDailog(AddTeacher.this, getResources().getString(R.string.pleasewait));
        final int random = new Random().nextInt(101) + 20;
        String userId = input_name.getText().toString().trim().replace(" ","");
        String rollNumber = (userId+""+random);
        String name = input_name.getText().toString().trim();
        String qualification = input_qualification.getText().toString().trim();
        String mobile = input_mobile.getText().toString().trim();
        String alternatenumber = input_alternatenumber.getText().toString().trim();
        String emailid = input_emailid.getText().toString().trim();
        String classtea = input_classtea.getText().toString().trim();
        String joindate = input_joindate.getText().toString().trim();
        String address = input_address.getText().toString().trim();
       retrofitDataProvider.addteacher(rollNumber, name, qualification, mobile, alternatenumber, emailid, classtea, joindate, address, new DownlodableCallback<StuTeaModel>() {
            @Override
            public void onSuccess(final StuTeaModel result) {
                //  closeDialog();
                Utilz.closeDialog();


                if (result.getStatus().contains(PreferenceName.TRUE)) {

                    Toast.makeText(AddTeacher.this, ""+result.getData(), Toast.LENGTH_SHORT).show();
                    finish();
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
