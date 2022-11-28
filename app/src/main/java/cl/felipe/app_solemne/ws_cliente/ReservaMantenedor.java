package cl.felipe.app_solemne.ws_cliente;

import android.content.Context;
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
import cl.felipe.app_solemne.Reserva;

public class ReservaMantenedor extends IMantenedor<Reserva> {

    public ListView listView;

    public ReservaMantenedor(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }

    @Override
    public Reserva save(Reserva reserva) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
        String url = apiUrl + "/reserva/crear";

        JSONObject body = new JSONObject();

        try {
            body.put("reservaId", reserva.getId());
            body.put("usuarioId", reserva.getUserId());
            body.put("libroId", reserva.getLibroId());

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
                                Toast t = Toast.makeText(context, "Reserva ingresada con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al ingresar reserva", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<Reserva> findAll(int id) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
        String url = apiUrl + "/reserva?usuarioId=" + id;
        JSONObject body = new JSONObject();

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

                                    data[i] =  "ID: " +o.get("id").toString() + " - Usuario: " + o.get("nombreUsuario").toString() + " " +
                                            " - Libro: " +
                                            o.get("nombreLibro").toString();
                                }

                                listArrayAdapter = new ArrayAdapter<String>(
                                        context, android.R.layout.simple_list_item_1, data
                                );
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
                Toast t = Toast.makeText(context, "Error al buscar reserva", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public Reserva find(int id) {
        return null;
    }

    @Override
    public List<Reserva> findAll() {
        return null;
    }


    @Override
    public void delete(int id) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
        }
        String url = apiUrl + "/reserva/borrar";

        JSONObject body = new JSONObject();

        try {
            body.put("reservaId", id);

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
                                Toast t = Toast.makeText(context, "Reserva eliminado con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al eliminar reserva", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reserva edit(Reserva reserva) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }

        String url = apiUrl + "/reserva/actualizar";

        JSONObject body = new JSONObject();

        try {
            body.put("reservaId", reserva.getId());
            body.put("usuarioId", reserva.getUserId());
            body.put("libroId", reserva.getLibroId());

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
                                Toast t = Toast.makeText(context, "Reserva actualizado con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al actualizar reserva", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }
}
