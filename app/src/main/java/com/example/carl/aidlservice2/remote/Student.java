package com.example.carl.aidlservice2.remote;

import android.os.Parcel;
import android.os.Parcelable;


public class Student implements Parcelable {
    private int id;
    private String name;
    private double price;

    public Student(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        //解包
        @Override
        public Student createFromParcel(Parcel in) {
            int id = in.readInt();
            String name = in.readString();
            double price = in.readDouble();
            return new Student(id,name,price);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    //打包
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
    }
}
