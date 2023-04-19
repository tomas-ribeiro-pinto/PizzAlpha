package com.tomaspinto.pizzalpha.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProductIngredient")
public class ProductIngredient implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int productIngredientId;
    @Embedded(prefix = "p_")
    public Product product;
    @Embedded(prefix = "p_")
    public Ingredient ingredient;

    public ProductIngredient(){}

    protected ProductIngredient(Parcel in) {
        productIngredientId = in.readInt();
        product = in.readParcelable(Product.class.getClassLoader());
        ingredient = in.readParcelable(Ingredient.class.getClassLoader());
    }

    public static final Creator<ProductIngredient> CREATOR = new Creator<ProductIngredient>() {
        @Override
        public ProductIngredient createFromParcel(Parcel in) {
            return new ProductIngredient(in);
        }

        @Override
        public ProductIngredient[] newArray(int size) {
            return new ProductIngredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(productIngredientId);
        dest.writeParcelable(product, flags);
        dest.writeParcelable(ingredient, flags);
    }
}
