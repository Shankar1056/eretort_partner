package apextechies.eretortpartner;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import apextechies.eretortpartner.activity.StuTeaMainActivity;
import apextechies.eretortpartner.adapter.HomeCategoryAdapter;
import apextechies.eretortpartner.common.ClsGeneral;
import apextechies.eretortpartner.common.PreferenceName;
import apextechies.eretortpartner.common.Utilz;
import apextechies.eretortpartner.intrface.OnClickListener;
import apextechies.eretortpartner.model.SchoolListModel;
import apextechies.eretortpartner.retrofit.DownlodableCallback;
import apextechies.eretortpartner.retrofit.RetrofitDataProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_school)
    RecyclerView rv_school;

    private RetrofitDataProvider retrofitDataProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ButterKnife.bind(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        retrofitDataProvider = new RetrofitDataProvider(this);

        rv_school.setLayoutManager(new LinearLayoutManager(this));
        getAllSchool();

    }

    private void getAllSchool() {
        Utilz.showDailog(MainActivity.this, getResources().getString(R.string.pleasewait));
        retrofitDataProvider.allschool(new DownlodableCallback<SchoolListModel>() {
            @Override
            public void onSuccess(final SchoolListModel result) {
                Utilz.closeDialog();

                if (result.getStatus().contains(PreferenceName.TRUE)) {

                    rv_school.setAdapter(new HomeCategoryAdapter(MainActivity.this, result.getData(), R.layout.activity_main_row, new OnClickListener() {
                        @Override
                        public void onClick(int pos) {
                            ClsGeneral.setPreferences(MainActivity.this, PreferenceName.DYNAMICURL, result.getData().get(pos).getUrl_link());
                            openDialog(result.getData().get(pos).getSchool_id());
                        }
                    }));
                }

            }

            @Override
            public void onFailure(String error) {
                Utilz.closeDialog();
            }

            @Override
            public void onUnauthorized(int errorNumber) {
                Utilz.closeDialog();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    /* @OnClick({R.id.tv_soft, R.id.tv_gurukul, R.id.tv_sps})
     void OntextClick(TextView textView){
         switch (textView.getId()){
             case R.id.tv_soft:
                 ClsGeneral.setPreferences(MainActivity.this, PreferenceName.SCHOOLNAME, PreferenceName.SOFTGALLI);
                 openDialog("soft_321");
                 break;

             case R.id.tv_gurukul:
                 ClsGeneral.setPreferences(MainActivity.this, PreferenceName.SCHOOLNAME, PreferenceName.GURUKUL);
                 openDialog("gurukul_246");
                 break;

             case R.id.tv_sps:
                 ClsGeneral.setPreferences(MainActivity.this, PreferenceName.SCHOOLNAME, PreferenceName.SPS);
                 openDialog("sps_986");
                 break;

         }
     }
 */
    private void openDialog(final String soft) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);
        final EditText code = (EditText) dialog.findViewById(R.id.code);
        Button submit = (Button) dialog.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.getText().toString().trim().equals(soft))
                {
                    dialog.dismiss();
                    startActivity(new Intent(MainActivity.this, StuTeaMainActivity.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid code", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        dialog.show();

    }
}
