package com.stark.mypratice.ipcdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String bookName;
    private int bookNumber;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Book(String bookName, int bookNumber) {
        this.bookName = bookName;
        this.bookNumber = bookNumber;
    }

    protected Book(Parcel in) {
        bookName = in.readString();
        bookNumber = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeInt(bookNumber);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookNumber=" + bookNumber +
                '}';
    }
}
