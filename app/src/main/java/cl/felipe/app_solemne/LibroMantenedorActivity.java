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

import cl.felipe.app_solemne.ws_cliente.CategoriaMantenedor;
import cl.felipe.app_solemne.ws_cliente.LibroMantenedor;

public class LibroMantenedorActivity extends ActivityConfigSensor {

    public LibroMantenedor mantenedor;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_mantenedor);
        this.listView = findViewById(R.id.librosList);
        this.mantenedor = new LibroMantenedor(this, this.listView);

    }


    public void create(View v) {

        String name = ((EditText) findViewById(R.id.txtLibroName)).getText().toString();
        int publisher = Integer.parseInt(((EditText) findViewById(R.id.txtLibroPublisher)).getText().toString());
        int autorId = Integer.parseInt(((EditText) findViewById(R.id.txtLibroAutorId)).getText().toString());
        int catId = Integer.parseInt(((EditText) findViewById(R.id.txtLibroCatId)).getText().toString());
        Libro a = new Libro(name, publisher, autorId, catId);
        mantenedor.save(a);

    }

    public void autorList(View v) {
        mantenedor.findAll();
    }

    public void delete(View v) {
        String name = ((EditText) findViewById(R.id.txtLibroName)).getText().toString();
        mantenedor.delete(name);
    }

    public void edit(View v) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.txtLibroId)).getText().toString());
        String name = ((EditText) findViewById(R.id.txtLibroName)).getText().toString();
        int publisher = Integer.parseInt(((EditText) findViewById(R.id.txtLibroPublisher)).getText().toString());
        int autorId = Integer.parseInt(((EditText) findViewById(R.id.txtLibroAutorId)).getText().toString());
        int catId = Integer.parseInt(((EditText) findViewById(R.id.txtLibroCatId)).getText().toString());
        Libro a = new Libro(id ,name, publisher, autorId, catId);
        mantenedor.edit(a);
    }

    public void back(View v) {
        Intent main = new Intent(this, MenuActivity.class);
        main.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(main);
    }


}