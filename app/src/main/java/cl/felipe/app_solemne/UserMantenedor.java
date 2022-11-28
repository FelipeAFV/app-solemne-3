package cl.felipe.app_solemne;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserMantenedor extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "books.db";
    private final String USER_TABLE = "CREATE TABLE users (" +
            " username TEXT PRIMARY KEY," +
            " password TEXT," +
            " firstname TEXT," +
            " lastname TEXT)";
    private final String BOOK_TABLE = "CREATE TABLE books (" +
            " title TEXT PRIMARY KEY," +
            " author TEXT," +
            " editorial TEXT)";

    private final String RESERVATION_TABLE = "CREATE TABLE reservations (" +
            " userId TEXT," +
            " bookId TEXT," +
            " FOREIGN KEY(bookId) REFERENCES books(title), " +
            " FOREIGN KEY(userId) REFERENCES users(username) )";

    public UserMantenedor(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(USER_TABLE);
        sqLiteDatabase.execSQL(BOOK_TABLE);
        sqLiteDatabase.execSQL(RESERVATION_TABLE);
        sqLiteDatabase.execSQL("" +
                "INSERT INTO books(title, author, editorial) VALUES('El Principito'," +
                "'Antoine de Saint-Exupéry', 'Océano')");
        sqLiteDatabase.execSQL("" +
                "INSERT INTO books(title, author, editorial) VALUES('El Señor de los Anillos'," +
                "'J. R. R. Tolkien', 'Tirant Lo Blanch')");
        sqLiteDatabase.execSQL("" +
                "INSERT INTO books(title, author, editorial) VALUES('Harry Potter'," +
                "'J. K. Rowling', 'Bloomsbury')");
        sqLiteDatabase.execSQL("" +
                "INSERT INTO books(title, author, editorial) VALUES('Romeo y Julieta'," +
                "'William Shakespeare', 'Juventúd')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public User findByUsername(String username) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        User user = null;

        Cursor auxCursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username = '" +username+"';", null);
        if (!auxCursor.moveToFirst()) {
            return null;
        }
        user = new User();
        user.setUsername(auxCursor.getString(0));
        user.setPassword(auxCursor.getString(1));

        auxCursor.close();
        sqLiteDatabase.close();

        return user;
    }


    public void saveUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL("INSERT INTO users(username, password, firstname, lastname) " +
                " values('"+ user.getUsername() +"', '"+ user.getPassword()+ "', '"
                + user.getFirstname()+"', '"+ user.getLastname()+"');");
        sqLiteDatabase.close();

    }
}
