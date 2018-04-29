package apextechies.eretortpartner.retrofit;


import apextechies.eretortpartner.model.SchoolListModel;
import apextechies.eretortpartner.model.StuTeaModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Shankar on 1/27/2018.
 */

public interface ApiRetrofitService {

    @POST(ApiUrl.ADDTEACHER)
    @FormUrlEncoded
    Call<StuTeaModel> addTeacher(@Field("teacher_id") String teacher_id, @Field("name") String name, @Field("qualification") String qualification
            , @Field("mobile_number") String mobile_number, @Field("alternate_number") String alternate_number, @Field("email_id") String email_id
            , @Field("classteacher_for") String classteacher_for, @Field("joining_date") String joining_date, @Field("address") String address);

    @POST(ApiUrl.ADDSTUDENT)
    @FormUrlEncoded
    Call<StuTeaModel> addStudent(@Field("regestration_id") String regestration_id, @Field("name") String name, @Field("email") String email
            , @Field("mobile") String mobile_number, @Field("class") String clas, @Field("sec") String classteacher_for,
                                 @Field("admission_date") String admission_date, @Field("residential_address") String residential_address);


    @GET(ApiUrl.ALLSCHOOLLIST)
    Call<SchoolListModel> schoolList();

}
