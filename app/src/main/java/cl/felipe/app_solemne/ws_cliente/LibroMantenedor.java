package cl.felipe.app_solemne.ws_cliente;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cl.felipe.app_solemne.Libro;
import cl.felipe.app_solemne.MenuActivity;

public class LibroMantenedor extends IMantenedor<Libro>  {

    public ListView listView;

    public LibroMantenedor(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }

    @Override
    public Libro save(Libro libro) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
        String url = apiUrl + "/libro/crear";

        JSONObject body = new JSONObject();

        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;

        }

        try {
            body.put("name", libro.getName());
            body.put("editorialId", libro.getEditorialId());
            body.put("autorId", libro.getAutorId());
            body.put("categoriaId", libro.getCategoriaId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Lammando al server");
        JsonObjectRequest request = new JsonObjectRequest(url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            try {
                                //handle your response
                                Toast t = Toast.makeText(context, "Libro ingresado con éxito", Toast.LENGTH_LONG);
                                t.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error llamando al server ");
                error.printStackTrace();
                Toast t = Toast.makeText(context, "Error al ingresar libro", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<Libro> findAll() {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
        String url = apiUrl + "/libro";

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (null != response) {
                            try {
                                String data[] = new String[response.length()];

                                ArrayAdapter<String> listArrayAdapter = null;
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject o = response.getJSONObject(i);

                                    data[i] = "ID: " +o.get("id").toString() + " - Nombre: " + o.get("name").toString() +
                                            " - Editorial:" +
                                            o.get("nombreEditorial").toString() + " - Autor:  " +  o.get("nombreAutor") + " -" +
                                            "Categoria:  " +
                                    o.get("nombreCategoria");
                                    System.out.println(o.get("name").toString());
                                }

                                listArrayAdapter = new ArrayAdapter<String>(
                                        context, android.R.layout.simple_list_item_1, data
                                );
                                System.out.println(listView);
                                listView.setAdapter(listArrayAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error llamando al server ");
                error.printStackTrace();
                Toast t = Toast.makeText(context, "Error al buscar libros", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<Libro> findAll(int id) {
        return null;
    }

    @Override
    public Libro find(int id) {
        return null;
    }


    @Override
    public void delete(String id) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
        }
        String url = apiUrl + "/libro/borrar";

        JSONObject body = new JSONObject();

        try {
            body.put("name", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Lammando al server");
        JsonObjectRequest request = new JsonObjectRequest(url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            try {
                                Toast t = Toast.makeText(context, "Libro eliminado con éxito", Toast.LENGTH_LONG);
                                t.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error llamando al server ");
                error.printStackTrace();
                Toast t = Toast.makeText(context, "Error al ingresar libro", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Libro edit(Libro libro) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }

        String url = apiUrl + "/libro/actualizar";

        JSONObject body = new JSONObject();

        try {
            body.put("id", libro.getId());
            body.put("name", libro.getName());
            body.put("editorialId", libro.getEditorialId());
            body.put("autorId", libro.getAutorId());
            body.put("categoriaId", libro.getCategoriaId());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("lLamando al server");
        JsonObjectRequest request = new JsonObjectRequest(url, body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            try {
                                //handle your response
                                Toast t = Toast.makeText(context, "Libro actualizado con éxito", Toast.LENGTH_LONG);
                                t.show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast t = Toast.makeText(context, "Error al actualizar libro", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

}
