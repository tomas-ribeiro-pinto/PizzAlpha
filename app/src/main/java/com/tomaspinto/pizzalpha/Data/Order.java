package com.tomaspinto.pizzalpha.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

@Entity(tableName = "Order")
@TypeConverters(DateConverter.class)
public class Order implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int orderId;
    @Embedded
    public Table table;
    @ColumnInfo(name = "guestNumber")
    public int guestNumber;
    @ColumnInfo(name = "date")
    public Date date;
    @ColumnInfo(name = "waiter")
    public String waiter;

    //public List<Product> products;

    public Order(){}

    protected Order(Parcel in) {
        orderId = in.readInt();
        guestNumber = in.readInt();
        waiter = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(orderId);
        dest.writeInt(guestNumber);
        dest.writeString(waiter);
    }
}

class DateConverter {

    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}


