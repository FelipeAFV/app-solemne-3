package cl.felipe.app_solemne;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import cl.felipe.app_solemne.ws_cliente.CategoriaMantenedor;
import cl.felipe.app_solemne.ws_cliente.ReservaMantenedor;

public class ReservaMantenedorActivity extends ActivityConfigSensor {


    public ReservaMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_mantenedor);
        this.listView = findViewById(R.id.reservasList);
        this.mantenedor = new ReservaMantenedor(this, this.listView);

    }


    public void create(View v) {

        int userId  = Integer.parseInt(getIntent().getStringExtra("userId"));
        int libroId = Integer.parseInt(((EditText) findViewById(R.id.txtReservaLibroId)).getText().toString());
        Reserva a = new Reserva(userId, libroId);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        int userId  = Integer.parseInt(getIntent().getStringExtra("userId"));
        mantenedor.findAll(userId);
    }

    public void delete(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtReservaId)).getText().toString());
        mantenedor.delete(id);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtReservaId)).getText().toString());
        int userId  = Integer.parseInt(getIntent().getStringExtra("userId"));
        int libroId = Integer.parseInt(((EditText) findViewById(R.id.txtReservaLibroId)).getText().toString());
        Reserva a = new Reserva(id, userId, libroId);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }

}
