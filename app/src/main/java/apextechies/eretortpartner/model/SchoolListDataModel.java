package apextechies.eretortpartner.model;

import com.google.gson.annotations.SerializedName;

public class SchoolListDataModel {

    public String getId() {
        return id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getPrincipal_name() {
        return principal_name;
    }

    public String getPrincipa_mobile() {
        return principa_mobile;
    }

    public String getPrincipa_ulternate_mobile() {
        return principa_ulternate_mobile;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getPincode() {
        return pincode;
    }

    public String getSchool_address() {
        return school_address;
    }

    public String getSelected_package() {
        return selected_package;
    }

    public String getRegistered_on() {
        return registered_on;
    }

    public String getUrl_link() {
        return url_link;
    }

    public String getStatus() {
        return status;
    }

    @SerializedName("id")
    String id;
    @SerializedName("school_id")
    String school_id;
    @SerializedName("school_name")
    String school_name;
    @SerializedName("principal_name")
    String principal_name;
    @SerializedName("principa_mobile")
    String principa_mobile;
    @SerializedName("principa_ulternate_mobile")
    String principa_ulternate_mobile;
    @SerializedName("state")
    String state;
    @SerializedName("district")
    String district;

    public String getCity() {
        return city;
    }

    @SerializedName("city")
    String city;
    @SerializedName("pincode")
    String pincode;
    @SerializedName("school_address")
    String school_address;
    @SerializedName("selected_package")
    String selected_package;
    @SerializedName("registered_on")
    String registered_on;
    @SerializedName("url_link")
    String url_link;
    @SerializedName("status")
    String status;
}
