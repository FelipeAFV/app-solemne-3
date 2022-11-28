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

import cl.felipe.app_solemne.Editorial;
import cl.felipe.app_solemne.ws_cliente.IMantenedor;
import cl.felipe.app_solemne.ws_cliente.MySingleton;

public class EditorialMantenedor extends IMantenedor<Editorial> {


    public ListView listView;

    public EditorialMantenedor(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }

    @Override
    public Editorial save(Editorial pais) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;

        }
        String url = apiUrl + "/editorial/crear";

        JSONObject body = new JSONObject();

        try {
            body.put("name", pais.getName());
            body.put("paisId", pais.getPaisId());

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
                                System.out.println("respuesta del server");
                                response.toString();
                                System.out.println(response.getString("data"));
                                Toast t = Toast.makeText(context, "Autor ingresado con éxito", Toast.LENGTH_LONG);
                                t.show();
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
                Toast t = Toast.makeText(context, "Error al ingresar autor", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<Editorial> findAll() {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;

        }
        String url = apiUrl + "/editorial";

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

                                    data[i] = "ID: "+o.get("id").toString() + " - Nombre: " + o.get("name").toString() + " - Pais: " +
                                            o.get("nombrePais").toString();
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
                Toast t = Toast.makeText(context, "Error al buscar autores", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }

    @Override
    public List<Editorial> findAll(int id) {
        return null;
    }

    @Override
    public Editorial find(int id) {
        return null;
    }


    @Override
    public void delete(String id) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
        }
        String url = apiUrl + "/editorial/borrar";

        JSONObject body = new JSONObject();

        try {
            body.put("id", id);

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
                                Toast t = Toast.makeText(context, "Autor eliminado con éxito", Toast.LENGTH_LONG);
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
                Toast t = Toast.makeText(context, "Error al ingresar autor", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
    }

    @Override
    public void delete(int id) {


        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
        }
        String url = apiUrl + "/editorial/borrar";

        JSONObject body = new JSONObject();

        try {
            body.put("id", id);

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
    public Editorial edit(Editorial autor) {
        if (!activeInternetConnection()) {
            Toast t = Toast.makeText(context, "Error: Conexión a internet requerida", Toast.LENGTH_LONG);
            t.show();
            return null;
        }

        String url = apiUrl + "/editorial/actualizar";

        JSONObject body = new JSONObject();

        try {
            body.put("id", autor.getId());
            body.put("name", autor.getName());
            body.put("paisId", autor.getPaisId());

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
                                System.out.println("respuesta del server");
                                response.toString();
                                System.out.println(response.getString("data"));
                                Toast t = Toast.makeText(context, "Autor actualizado con éxito", Toast.LENGTH_LONG);
                                t.show();
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
                Toast t = Toast.makeText(context, "Error al actualizar autor", Toast.LENGTH_LONG);
                t.show();
            }
        });
        MySingleton.getInstance(this.context).addToRequestQueue(request);
        return null;
    }
}
