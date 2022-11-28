package cl.felipe.app_solemne;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class ReservationsTransaccion  {

    private ReservationMantenedor reservationMantenedor;

    public ReservationsTransaccion(Context context) {
        this.reservationMantenedor = new ReservationMantenedor(context);
    }

    public void makeReservation(String bookId, String username) throws ReservationException {

        List<Reservation> reservationList = reservationMantenedor.findAllByBookId(bookId);

        for (Reservation reservation : reservationList) {
            System.out.println(username);
            System.out.println("Username " + reservation.getUserId() + " Libro " + reservation.getBookId());
            if (reservation != null && reservation.getUserId().equals(username)) {
                System.out.println("lanzando error");
                throw new ReservationException("El libro ya se encuentra reservado");
            }

        }
        reservationMantenedor.createReservation(username, bookId);

    }

}
