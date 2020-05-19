package co.ocha.tugasprogmob.Food;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodModel implements Parcelable {
    private String foodName;
    private int foodImage;
    private String foodMap;
    private String foodRecipte;
    private String foodDesc;
    private String foodContact;

    public String getFoodContact() {
        return foodContact;
    }

    public void setFoodContact(String foodContact) {
        this.foodContact = foodContact;
    }



    protected FoodModel(Parcel in) {
        foodName = in.readString();
        foodImage = in.readInt();
        foodMap = in.readString();
        foodRecipte = in.readString();
        foodDesc = in.readString();
        foodContact=in.readString();
    }

    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel in) {
            return new FoodModel(in);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }



    public FoodModel(String foodName, int foodImage, String foodMap, String foodRecipte, String foodDesc,String foodContact) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodMap = foodMap;
        this.foodRecipte = foodRecipte;
        this.foodContact = foodContact;
        this.foodDesc = foodDesc;
    }

    public FoodModel() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodMap() {
        return foodMap;
    }

    public void setFoodMap(String foodMap) {
        this.foodMap = foodMap;
    }

    public String getFoodRecipte() {
        return foodRecipte;
    }

    public void setFoodRecipte(String foodRecipte) {
        this.foodRecipte = foodRecipte;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodName);
        dest.writeInt(foodImage);
        dest.writeString(foodMap);
        dest.writeString(foodRecipte);
        dest.writeString(foodDesc);
        dest.writeString(foodContact);
    }
}
