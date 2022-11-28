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

import cl.felipe.app_solemne.ws_cliente.ReclamoMantenedor;

public class ReclamoMantenedorActivity extends ActivityConfigSensor {
    public ReclamoMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_mantenedor);
        this.listView = findViewById(R.id.reservasList);
        this.mantenedor = new ReclamoMantenedor(this, this.listView);

    }


    public void create(View v) {

        int userId  = Integer.parseInt(getIntent().getStringExtra("userId"));
        int libroId = Integer.parseInt(((EditText) findViewById(R.id.txtReclamoLibroId)).getText().toString());
        String comment = ((EditText) findViewById(R.id.txtReclamoComment)).getText().toString();
        Reclamo a = new Reclamo(comment, userId, libroId);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        //int userId = Integer.parseInt(((EditText) findViewById(R.id.txtReclamoUserId)).getText().toString());
        int userId  = Integer.parseInt(getIntent().getStringExtra("userId"));
        mantenedor.findAll(userId);
    }

    public void delete(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtReclamoId)).getText().toString());
        mantenedor.delete(id);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtReclamoId)).getText().toString());
        String comment = ((EditText) findViewById(R.id.txtReclamoComment)).getText().toString();
        Reclamo a = new Reclamo(id, comment);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }


}
