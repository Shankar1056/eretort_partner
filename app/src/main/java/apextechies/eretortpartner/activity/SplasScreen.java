package apextechies.eretortpartner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import apextechies.eretortpartner.MainActivity;
import apextechies.eretortpartner.R;
import apextechies.eretortpartner.common.ClsGeneral;
import apextechies.eretortpartner.common.PreferenceName;


public class SplasScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splasscreen);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    ClsGeneral.setPreferences(SplasScreen.this, PreferenceName.DYNAMICURL, "");
                        startActivity(new Intent(SplasScreen.this, MainActivity.class));
                        finish();

                }
            }
        };
        timer.start();
    }

}
