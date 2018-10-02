package com.tavares.projetoculinaria.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Receitas implements   Parcelable{

    private int mId;
    private String mName = "";
    private String mServings= "";
    private String mImage= "";

    private ArrayList<Ingredientes> ingredientesArrayList;
    private ArrayList<Steps> stepsArrayList;


    public Receitas() {
    }

    public Receitas(int mId, String mName, String mServings, String mImage, ArrayList<Ingredientes> ingredientesArrayList, ArrayList<Steps> stepsArrayList) {
        this.mId = mId;
        this.mName = mName;
        this.mServings = mServings;
        this.mImage = mImage;
        this.ingredientesArrayList = ingredientesArrayList;
        this.stepsArrayList = stepsArrayList;
    }

    protected Receitas(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mServings = in.readString();
        mImage = in.readString();
        ingredientesArrayList = in.createTypedArrayList(Ingredientes.CREATOR);
        stepsArrayList = in.createTypedArrayList(Steps.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mServings);
        dest.writeString(mImage);
        dest.writeTypedList(ingredientesArrayList);
        dest.writeTypedList(stepsArrayList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Receitas> CREATOR = new Creator<Receitas>() {
        @Override
        public Receitas createFromParcel(Parcel in) {
            return new Receitas(in);
        }

        @Override
        public Receitas[] newArray(int size) {
            return new Receitas[size];
        }
    };

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmServings() {
        return mServings;
    }

    public void setmServings(String mServings) {
        this.mServings = mServings;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public ArrayList<Ingredientes> getIngredientesArrayList() {
        return ingredientesArrayList;
    }

    public void setIngredientesArrayList(ArrayList<Ingredientes> ingredientesArrayList) {
        this.ingredientesArrayList = ingredientesArrayList;
    }

    public ArrayList<Steps> getStepsArrayList() {
        return stepsArrayList;
    }

    public void setStepsArrayList(ArrayList<Steps> stepsArrayList) {
        this.stepsArrayList = stepsArrayList;
    }


    @Override
    public String toString() {
        return "Receitas{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mServings='" + mServings + '\'' +
                ", mImage='" + mImage + '\'' +
                ", ingredientesArrayList=" + ingredientesArrayList +
                ", stepsArrayList=" + stepsArrayList +
                '}';
    }
}