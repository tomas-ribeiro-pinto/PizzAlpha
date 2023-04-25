package com.tomaspinto.pizzalpha.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.tomaspinto.pizzalpha.Category;

import java.util.List;

/**
 * Product Class for menu database
 */
@Entity(tableName = "Product")
public class Product implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int productId;
    @ColumnInfo(name = "title")
    public String name;
    @ColumnInfo(name = "size")
    public String size;
    @ColumnInfo(name = "tax")
    public double tax;
    @ColumnInfo(name = "basePrice")
    public double basePrice;
    @ColumnInfo(name = "notes")
    public String notes;
    @ColumnInfo(name = "category")
    public Category category;

    //public List<Order> orders;
    //public List<Ingredient> ingredients;

    public Product(){}

    protected Product(Parcel in) {
        productId = in.readInt();
        name = in.readString();
        size = in.readString();
        tax = in.readDouble();
        basePrice = in.readDouble();
        notes = in.readString();
        //orders = in.createTypedArrayList(Order.CREATOR);
        //ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeString(name);
        dest.writeString(size);
        dest.writeDouble(tax);
        dest.writeDouble(basePrice);
        dest.writeString(notes);
        //dest.writeTypedList(orders);
        //dest.writeTypedList(ingredients);
    }
}
