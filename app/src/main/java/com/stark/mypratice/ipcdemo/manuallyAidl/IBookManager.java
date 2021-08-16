package com.stark.mypratice.ipcdemo.manuallyAidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.stark.mypratice.ipcdemo.Book;

import java.util.List;

public interface IBookManager extends IInterface {

    static final String DESCRIPTOR = "com.tony.ipcdemo.IBookManager";

    static final int TRANSACTION_addBook = (IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getBookList = (IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_addBookAndGetNumber = (IBinder.FIRST_CALL_TRANSACTION + 2);

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    public void addBook(Book book) throws RemoteException;

    public List<Book> getBookList() throws RemoteException;

    public int addBookAndGetNumber(Book book) throws RemoteException;

}
