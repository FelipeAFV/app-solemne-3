package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import cl.felipe.app_solemne.ws_cliente.AutorMantenedor;

public class AutorMantenedorActivity extends ActivityConfigSensor  {

    public AutorMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor_mantenedor);
        this.listView = findViewById(R.id.autorList);
        this.mantenedor = new AutorMantenedor(this, this.listView);

    }


    public void create(View v) {

        String firstName = ((EditText) findViewById(R.id.txtAutorFirstname)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.txtAutorLastname)).getText().toString();
        Autor a = new Autor(firstName, lastName);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        mantenedor.findAll();
    }

    public void delete(View v) {
        String firstName = ((EditText) findViewById(R.id.txtAutorFirstname)).getText().toString();
        mantenedor.delete(firstName);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtAutorId)).getText().toString());
        String firstName = ((EditText) findViewById(R.id.txtAutorFirstname)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.txtAutorLastname)).getText().toString();
        Autor a = new Autor(id, firstName, lastName);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }
}