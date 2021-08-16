package com.stark.mypratice.ipcdemo.manuallyAidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;


import androidx.annotation.NonNull;

import com.stark.mypratice.ipcdemo.Book;
import com.stark.mypratice.ipcdemo.utils.ProcessUtils;

import java.util.ArrayList;
import java.util.List;

public class BookManagerImpl extends Binder implements IBookManager {

    private static final String TAG = "BookManagerImpl";

    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IBookManager asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (iin == null && (iin instanceof IBookManager) ) {
            return (IBookManager) iin;
        }
        return new Proxy(obj);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, Parcel reply, int flags) throws RemoteException {
        Log.d(TAG + "lpq", "onTransact: Process = " + ProcessUtils.getProcessName());
        Log.d(TAG + "lpq", "onTransact: Thread = " + Thread.currentThread().getName());
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_addBook:{
                data.enforceInterface(DESCRIPTOR);
                Book arg0;
                if (0 != data.readInt()) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    arg0 = null;
                }
                addBook(arg0);
                reply.writeNoException();
                return true;
            }
            case TRANSACTION_getBookList:{
                data.enforceInterface(DESCRIPTOR);
                List<Book> bookList = getBookList();
                reply.writeNoException();
                reply.writeTypedList(bookList);
                return true;
            }
            case TRANSACTION_addBookAndGetNumber:{
                data.enforceInterface(DESCRIPTOR);
                Book arg0;
                if (0 != data.readInt()) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    arg0 = null;
                }
                addBook(arg0);
                reply.writeNoException();
                reply.writeInt(getBookList().size());
                return true;
            }

        }
        return super.onTransact(code, data, reply, flags);
    }

    public static class Proxy implements IBookManager {

        private IBinder mRemote;

        public Proxy(IBinder mRemote) {
            this.mRemote = mRemote;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if (book != null) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                reply.readException();
            } finally {
                data.recycle();
                reply.recycle();
            }
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            List<Book> bookList;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getBookList, data, reply, 0);
                reply.readException();
                bookList = reply.createTypedArrayList(Book.CREATOR);
            } finally {
                data.recycle();
                reply.recycle();
            }
            return bookList;
        }

        @Override
        public int addBookAndGetNumber(Book book) throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            int bookNumber = 0;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if (book != null) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBookAndGetNumber, data, reply, 0);
                reply.readException();
                bookNumber = reply.readInt();
            } finally {
                data.recycle();
                reply.recycle();
            }
            return bookNumber;
        }


    }

    ArrayList<Book> books = new ArrayList<>();
    @Override
    public void addBook(Book book) throws RemoteException {
        books.add(book);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return books;
    }

    @Override
    public int addBookAndGetNumber(Book book) throws RemoteException {
        books.add(book);
        return books.size();
    }

    @Override
    public IBinder asBinder() {
        return this;
    }
}
