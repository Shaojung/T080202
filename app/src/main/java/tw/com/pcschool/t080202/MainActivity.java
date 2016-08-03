package tw.com.pcschool.t080202;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        // String url = "http://data.taipei/opendata/datalist/datasetMeta/download?id=6556e1e8-c908-42d5-b984-b3f7337b139b&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b";
        // String url = "http://udn.com/rssfeed/news/1";
        // String url = "http://data.ntpc.gov.tw/od/data/api/E09B35A5-A738-48CC-B0F5-570B67AD9C78?$format=json";
        String url = "http://data.ntpc.gov.tw/od/data/api/B1464EF0-9C7C-4A6F-ABF7-6BDF32847E68?$format=json";
        StringRequest request = new UTF8StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Log.d("NET", response);
                        try {
                            JSONArray array = new JSONArray(response);
                            JSONObject obj = array.getJSONObject(0);
                            Log.d("NET", "Name:" + obj.getString("NAME"));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("NET", "Error:" + error.toString());
            }
        });

        queue.add(request);
        queue.start();
        Log.d("NET", "Volley started");
    }
}
