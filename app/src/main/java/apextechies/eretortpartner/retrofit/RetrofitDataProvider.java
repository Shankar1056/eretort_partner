package apextechies.eretortpartner.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import apextechies.eretortpartner.common.ClsGeneral;
import apextechies.eretortpartner.common.PreferenceName;
import apextechies.eretortpartner.model.SchoolListModel;
import apextechies.eretortpartner.model.StuTeaModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shankar on 1/27/2018.
 */

public class RetrofitDataProvider extends AppCompatActivity implements ServiceMethods {
    private Context context;
    private String url;

    private ApiRetrofitService createRetrofitService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        if (ClsGeneral.getPreferences(context, PreferenceName.DYNAMICURL).equals("")){
            url = ApiUrl.BASE_URL;
        }else{
            url = ClsGeneral.getPreferences(context, PreferenceName.DYNAMICURL);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiRetrofitService.class);
    }

    public RetrofitDataProvider(Context context)
    {
        this.context = context;
    }



    @Override
    public void addteacher(String teacher_id, String name, String qualification, String mobile_number, String alternate_number, String email_id, String classteacher_for, String joining_date, String address, final DownlodableCallback<StuTeaModel> callback) {
        createRetrofitService().addTeacher(teacher_id, name, qualification, mobile_number, alternate_number, email_id, classteacher_for, joining_date, address).enqueue(
                new Callback<StuTeaModel>() {
                    @Override
                    public void onResponse(@NonNull Call<StuTeaModel> call, @NonNull final Response<StuTeaModel> response) {
                        if (response.isSuccessful()) {

                            StuTeaModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StuTeaModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }

    @Override
    public void addstudent(String regestration_id, String name, String email, String mobile, String clas, String sec, String admission_date, String residential_address, final DownlodableCallback<StuTeaModel> callback) {
        createRetrofitService().addStudent(regestration_id, name, email, mobile, clas, sec, admission_date, residential_address).enqueue(
                new Callback<StuTeaModel>() {
                    @Override
                    public void onResponse(@NonNull Call<StuTeaModel> call, @NonNull final Response<StuTeaModel> response) {
                        if (response.isSuccessful()) {

                            StuTeaModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StuTeaModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }

    @Override
    public void allschool(final DownlodableCallback<SchoolListModel> callback) {
        createRetrofitService().schoolList().enqueue(
                new Callback<SchoolListModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SchoolListModel> call, @NonNull final Response<SchoolListModel> response) {
                        if (response.isSuccessful()) {

                            SchoolListModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SchoolListModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }


}