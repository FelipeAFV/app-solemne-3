package cl.felipe.app_solemne;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ReservationMantenedor extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "books.db";


    public ReservationMantenedor(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Reservation> findAllByUser(String username) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM reservations WHERE userId = '" + username +"';", null);

        List<Reservation> reservations = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return null;
        }
        do {
            String userId = cursor.getString(0);
            String bookId = cursor.getString(1);
            reservations.add(new Reservation(userId, bookId));
        } while (cursor.moveToNext());

        return reservations;
    }

    public List<Reservation> findAllByBookId(String id) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT userId, bookId FROM reservations WHERE bookId = '" + id +"';", null);

        List<Reservation> reservationList = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return null;
        }
        do {
            String userId = cursor.getString(0);
            String bookId = cursor.getString(1);
            Reservation r = new Reservation(userId, bookId);
            reservationList.add(r);

        } while (cursor.moveToNext());

        cursor.close();
        sqLiteDatabase.close();
        return reservationList;
    }

    public void createReservation(String username, String bookId) {

        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.execSQL("INSERT INTO reservations(userId, bookId) values(" +
                    "'"+ username+ "'," +
                    "'"+ bookId+ "')");
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
