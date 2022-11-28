package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ReservationListActivity extends AppCompatActivity {

    private ReservationMantenedor reservationMantenedor = new ReservationMantenedor(this);
    private BookMantenedor bookMantenedor = new BookMantenedor(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);


        try {

            ListView listView = findViewById(R.id.reservationsList);

            List<Reservation> reservations = reservationMantenedor.findAllByUser(getIntent().getStringExtra("user"));

            ArrayAdapter<String> listArrayAdapter = null;
            if (reservations != null && reservations.size() > 0) {


                String data[] = new String[reservations.size()];
                int i = 0;


                for (Reservation r : reservations) {
                    Libro b = bookMantenedor.findByTitle(r.getBookId());
                    data[i] = b.getName();
                    i = i +1;
                }
                listArrayAdapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_list_item_1, data
                );

            } else {
                String data[] = new String[1];
                data[0] = "Aún no tienes reservas";
                listArrayAdapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_list_item_1, data
                );
            }
            listView.setAdapter(listArrayAdapter);
        } catch (Exception e) {
            message("Error al buscar reservas");
        }
    }

    public void back(View v) {
        Intent intent = new Intent(this, ReservationsActivity.class);
        intent.putExtra("user", getIntent().getStringExtra("user"));
        startActivity(intent);
    }

    private void message(String textMessage) {
        Toast t = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);
        t.show();
    }
}