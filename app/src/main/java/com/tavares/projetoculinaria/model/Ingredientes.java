package com.tavares.projetoculinaria.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredientes implements Parcelable {
    private int mId;
    private String quantity= "";
    private String measure= "";
    private String ingredient= "";
    private String name = "";

    public Ingredientes() {
    }

    public Ingredientes(int mId, String quantity, String measure, String ingredient) {
        this.mId = mId;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }



    private Ingredientes(Parcel in) {
        mId = in.readInt();
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ingredientes> CREATOR = new Creator<Ingredientes>() {
        @Override
        public Ingredientes createFromParcel(Parcel in) {
            return new Ingredientes(in);
        }

        @Override
        public Ingredientes[] newArray(int size) {
            return new Ingredientes[size];
        }
    };

    @Override
    public String toString() {
        return "Ingredientes{" +
                "mId=" + mId +
                ", quantity='" + quantity + '\'' +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
