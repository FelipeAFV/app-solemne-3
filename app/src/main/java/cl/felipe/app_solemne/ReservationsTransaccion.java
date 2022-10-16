package cl.felipe.app_solemne;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ReservationsTransaccion  {

    private ReservationMantenedor reservationMantenedor;

    public ReservationsTransaccion(Context context) {
        this.reservationMantenedor = new ReservationMantenedor(context);
    }

    public void makeReservation(String bookId, String username) throws ReservationException {

        Reservation reservation = reservationMantenedor.findByBookId(bookId);

        if (reservation != null) {
            throw new ReservationException("El libro ya se encuentra reservado");
        }
        reservationMantenedor.createReservation(username, bookId);

    }

}
