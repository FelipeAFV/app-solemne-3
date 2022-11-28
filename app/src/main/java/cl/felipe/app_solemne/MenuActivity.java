package cl.felipe.app_solemne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void irAutor(View v) {

        Intent autorActivity = new Intent(this, AutorMantenedorActivity.class);
        autorActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(autorActivity);
    }

    public void irCategoria(View v) {

        Intent categoriaActivity = new Intent(this, CategoriaMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);
    }


    public void irLibro(View v) {

        Intent libroActivity = new Intent(this, LibroMantenedorActivity.class);
        libroActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(libroActivity);
    }

    public void irReserva(View v) {

        Intent categoriaActivity = new Intent(this, ReservaMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);
    }
    public void irReclamo(View v) {

        Intent categoriaActivity = new Intent(this, ReclamoMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);

    }

    public void irPais(View v) {

        Intent categoriaActivity = new Intent(this, PaisMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);

    }

    public void irEditorial(View v) {

        Intent categoriaActivity = new Intent(this, EditorialMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);

    }
    public void irRecomendacion(View v) {

        Intent categoriaActivity = new Intent(this, RecomendacionMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);

    }
    public void irOpinion(View v) {

        Intent categoriaActivity = new Intent(this, OpinionMantenedorActivity.class);
        categoriaActivity.putExtra("userId", getIntent().getStringExtra("userId"));
        startActivity(categoriaActivity);

    }

}