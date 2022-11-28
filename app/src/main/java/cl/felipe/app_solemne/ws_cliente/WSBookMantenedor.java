package cl.felipe.app_solemne.ws_cliente;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import cl.felipe.app_solemne.Libro;
import cl.felipe.app_solemne.Libro;

public class WSBookMantenedor implements IBookMantenedor{

    private Context context;

    public WSBookMantenedor(Context context) {
        this.context = context;
    }

    @Override
    public Libro saveBook() {
        String url ="http://10.0.2.2:8000/api/v1/auth/ingresar";

        JSONObject body = new JSONObject();

        try {
            body.put("username", "director");
            body.put("password", "director");

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
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
        return null;
    }

    @Override
    public Libro updateBook() {
        return null;
    }
}
