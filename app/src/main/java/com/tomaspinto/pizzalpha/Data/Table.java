package com.tomaspinto.pizzalpha.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Table")
public class Table implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int tableId;
    @ColumnInfo(name = "tableName")
    public String tableName;

    public Table(){}

    protected Table(Parcel in) {
        tableId = in.readInt();
        tableName = in.readString();
    }

    public static final Creator<Table> CREATOR = new Creator<Table>() {
        @Override
        public Table createFromParcel(Parcel in) {
            return new Table(in);
        }

        @Override
        public Table[] newArray(int size) {
            return new Table[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(tableId);
        dest.writeString(tableName);
    }
}
