package cl.felipe.app_solemne;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserMantenedor extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "app-solemne.db";
    private final String USER_TABLE = "CREATE TABLE users (" +
            " username TEXT PRIMARY KEY," +
            " password TEXT," +
            " firstname TEXT," +
            " lastname TEXT)";

    public UserMantenedor(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(USER_TABLE);
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
