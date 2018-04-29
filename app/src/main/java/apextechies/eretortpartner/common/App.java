package apextechies.eretortpartner.common;

import android.app.Application;

import apextechies.eretortpartner.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Shankar on 3/31/2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.roboto_medium))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
