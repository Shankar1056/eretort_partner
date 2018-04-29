package apextechies.eretortpartner.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SchoolListModel {

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<SchoolListDataModel> getData() {
        return data;
    }

    @SerializedName("status")
    String status;
    @SerializedName("msg")
    String msg;
    @SerializedName("data")
    ArrayList<SchoolListDataModel> data;

}
