package co.ocha.tugasprogmob.Restaurant;

import android.os.Parcel;
import android.os.Parcelable;

public class RestaurantModel implements Parcelable {
    private String resName;
    private int resImage;
    private String resMap;
    private String resMenu;
    private String resDesc;
    private String resContact;

    public String getResName() {
        return resName;
    }

    public RestaurantModel(String resName, int resImage, String resMap, String resMenu, String resDesc, String resContact) {
        this.resName = resName;
        this.resImage = resImage;
        this.resMap = resMap;
        this.resMenu = resMenu;
        this.resDesc = resDesc;
        this.resContact = resContact;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public String getResMap() {
        return resMap;
    }

    public void setResMap(String resMap) {
        this.resMap = resMap;
    }

    public String getResMenu() {
        return resMenu;
    }

    public void setResMenu(String resMenu) {
        this.resMenu = resMenu;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getResContact() {
        return resContact;
    }

    public void setResContact(String resContact) {
        this.resContact = resContact;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resName);
        dest.writeInt(this.resImage);
        dest.writeString(this.resMap);
        dest.writeString(this.resMenu);
        dest.writeString(this.resDesc);
        dest.writeString(this.resContact);
    }

    public RestaurantModel() {
    }

    protected RestaurantModel(Parcel in) {
        this.resName = in.readString();
        this.resImage = in.readInt();
        this.resMap = in.readString();
        this.resMenu = in.readString();
        this.resDesc = in.readString();
        this.resContact = in.readString();
    }

    public static final Parcelable.Creator<RestaurantModel> CREATOR = new Parcelable.Creator<RestaurantModel>() {
        @Override
        public RestaurantModel createFromParcel(Parcel source) {
            return new RestaurantModel(source);
        }

        @Override
        public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
        }
    };
}
