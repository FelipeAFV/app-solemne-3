package cl.felipe.app_solemne;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookMantenedor extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "booksapp.db";


    public BookMantenedor(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("" +
                "INSERT INTO books(id, title, author, editorial) VALUES(1, 'El Principito'," +
                "'Antoine de Saint-Exupéry', 'Océano')");

    }

    public Book findByTitle(String title) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Book book = null;

        Cursor auxCursor = sqLiteDatabase.rawQuery("SELECT * FROM books WHERE title = '" + title +"';", null);
        if (!auxCursor.moveToFirst()) {
            return null;
        }
        book = new Book();
        book.setTitle(auxCursor.getString(0));
        book.setAuthor(auxCursor.getString(1));
        book.setEditorial(auxCursor.getString(2));

        auxCursor.close();
        sqLiteDatabase.close();

        return book;
    }

    public List<Book> findAll() {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            List<Book> books = new ArrayList<>();

            Cursor auxCursor = sqLiteDatabase.rawQuery("SELECT * FROM books;", null);
            if (!auxCursor.moveToFirst()) {
                return null;
            }

            do {
                Book book = new Book();
                book.setTitle(auxCursor.getString(0));
                book.setAuthor(auxCursor.getString(1));
                book.setEditorial(auxCursor.getString(2));
                books.add(book);

            } while (auxCursor.moveToNext());

            auxCursor.close();
            sqLiteDatabase.close();

            return books;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
