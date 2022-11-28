package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ReservationsActivity extends AppCompatActivity {


    private BookMantenedor bookMantenedor = new BookMantenedor(this);

    private ReservationsTransaccion reservationsTransaccion = new ReservationsTransaccion(this);

    public ReservationsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bookMantenedor.findAll();
        setContentView(R.layout.activity_reservations);

        Spinner spinner = findViewById(R.id.spinnerBooks);

        List<Libro> books = bookMantenedor.findAll();
        if (books != null && books.size() != 0) {

            int totalBooks = books.size();

            String data[] = new String[totalBooks];
            int i = 0;
            for (Libro b : books) {
                data[i] = b.getName();
                i++;
            }



            ArrayAdapter<String>  spinnerArrayAdapter =new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, data
            );
            spinner.setAdapter(spinnerArrayAdapter);
        }
    }


    public void makeReservation(View v) {

        try {
            String userId = getIntent().getStringExtra("user");
            System.out.println("User id " + userId);
            Spinner spinner = findViewById(R.id.spinnerBooks);
            String bookTitle = (String) spinner.getSelectedItem();
            reservationsTransaccion.makeReservation(bookTitle, userId);
            message("Libro reservado");
        } catch (ReservationException e) {
            e.printStackTrace();
            message("El libro ya se encuentra reservado");
        } catch (Exception e) {
            e.printStackTrace();
            message("Error al reservar libro");
        }


    }

    public void goToReservations(View v) {

        Intent intent = new Intent(this, ReservationListActivity.class);
        intent.putExtra("user", getIntent().getStringExtra("user"));
        startActivity(intent);
    }

    private void message(String textMessage) {
        Toast t = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);
        t.show();
    }
}