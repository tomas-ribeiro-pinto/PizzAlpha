package com.tomaspinto.pizzalpha.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(tableName = "OrderProduct")
public class OrderProduct implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int orderProductId;
    @Embedded(prefix = "o_")
    public Product o_product;
    @Embedded(prefix = "o_")
    public Order order;

    public OrderProduct(){
    }

    protected OrderProduct(Parcel in) {
        orderProductId = in.readInt();
        o_product = in.readParcelable(Product.class.getClassLoader());
        order = in.readParcelable(Order.class.getClassLoader());
    }

    public static final Creator<OrderProduct> CREATOR = new Creator<OrderProduct>() {
        @Override
        public OrderProduct createFromParcel(Parcel in) {
            return new OrderProduct(in);
        }

        @Override
        public OrderProduct[] newArray(int size) {
            return new OrderProduct[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(orderProductId);
        dest.writeParcelable(o_product, flags);
        dest.writeParcelable(order, flags);
    }

    public static HashMap<Integer, Integer> getSlipDetails(List<OrderProduct> products)
    {
        HashMap<Integer, Integer> idSlip = new HashMap<Integer, Integer>();

        for(OrderProduct product : products)
        {
            if(idSlip.containsKey(product.o_product.productId))
            {
                idSlip.put(product.o_product.productId, idSlip.get(product.o_product.productId) + 1);
            }
            else
                idSlip.put(product.o_product.productId, 1);
        }

        return idSlip;
    }
}
