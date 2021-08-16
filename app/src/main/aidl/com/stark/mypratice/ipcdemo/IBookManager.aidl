// IBookManager.aidl
package com.stark.mypratice.ipcdemo;
import com.stark.mypratice.ipcdemo.Book;
import com.stark.mypratice.ipcdemo.IOnNewBookArrivedListener;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void addBook(in Book book);

   List<Book> getBookList();

   int addBookAndGetNumber(in Book book);

   void registerListener(IOnNewBookArrivedListener listener);
   void unregisterListener(IOnNewBookArrivedListener listener);
}
