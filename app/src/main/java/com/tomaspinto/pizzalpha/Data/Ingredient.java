package com.tomaspinto.pizzalpha.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity(tableName = "Ingredient")
public class Ingredient implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int ingredientId;
    @ColumnInfo(name = "type")
    public String type;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "cost")
    public double cost;

    public Ingredient(){}

    protected Ingredient(Parcel in) {
        ingredientId = in.readInt();
        type = in.readString();
        name = in.readString();
        cost = in.readDouble();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ingredientId);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeDouble(cost);
    }
}
