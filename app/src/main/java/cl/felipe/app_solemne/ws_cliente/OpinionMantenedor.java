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

import cl.felipe.app_solemne.Opinion;
import cl.felipe.app_solemne.Reclamo;
import cl.felipe.app_solemne.ws_cliente.IMantenedor;

public class OpinionMantenedor extends IMantenedor<Opinion> {


    public ListView listView;

    public OpinionMantenedor(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }

    @Override
    public Opinion save(Opinion reclamo) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
        String url = apiUrl + "/opinion/crear";

        JSONObject body = new JSONObject();

        try {
            body.put("comment", reclamo.getComment());
            body.put("usuarioId", reclamo.getUsuarioId());
            body.put("libroId", reclamo.getLibroId());

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
                                Toast t = Toast.makeText(context, "Reclamo ingresado con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al ingresar reclamo", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<Opinion> findAll(int id) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }
        String url = apiUrl + "/opinion?libroId=" + id;
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

                                    data[i] =  "ID: " + o.get("id").toString() + " - Usuario: " + o.get("nombreUsuario").toString() + " " +
                                            " - Libro: " +
                                            o.get("nombreLibro").toString() + " - Comentario: " +o.get("comment").toString()  ;
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
                Toast t = Toast.makeText(context, "Error al buscar reclamo", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public Opinion find(int id) {
        return null;
    }

    @Override
    public List<Opinion> findAll() {
        return null;
    }


    @Override
    public void delete(int id) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
        }
        String url = apiUrl + "/opinion/borrar";

        JSONObject body = new JSONObject();

        try {
            body.put("opinionId", id);

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
                                Toast t = Toast.makeText(context, "Reclamo eliminado con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al eliminar reclamo", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Opinion edit(Opinion reclamo) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }

        String url = apiUrl + "/opinion/actualizar";

        JSONObject body = new JSONObject();

        try {
            body.put("opinionId", reclamo.getId());
            body.put("comment", reclamo.getComment());

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
                                Toast t = Toast.makeText(context, "Reclamo actualizado con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al actualizar reclamo", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

}
