/*VolleyMethod Android

There are three methods to get data from the server in android

1. volley method
2. retrofit method
3. async task method

Here in this snippet, we will see a volley method To start with the same, add below dependency in-app level gradle file

1. compile 'com.android.volley:volley:1.1.1' -> adding volley dependency.
2. implementation 'com.google.code.gson:gson:2.8.5' -> adding gson for JSON data manipulation in android.

Dummy URL -> https://jsonplaceholder.typicode.com/users (HTTP GET Method Request)

-By Jaina A Trivedi
*/

public class VolleyActivity extends AppCompatActivity {
     
    RequestQueue mRequestQueue;
    Context context;
    Activity activity; 
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        context = this;
        getdata();
    }

    public void getdata(){
        Response.Listener<String> response_listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response",response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0).getJSONObject("address").getJSONObject("geo");
                    Log.e("lat",jsonObject.getString("lat");
                    Log.e("lng",jsonObject.getString("lng");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        Response.ErrorListener response_error_listener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //TODO
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    //TODO
                } else if (error instanceof NetworkError) {
                    //TODO
                } else if (error instanceof ParseError) {
                    //TODO
                }
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users",response_listener,response_error_listener);
        getRequestQueue().add(stringRequest);
    }   
    
    public RequestQueue getRequestQueue() {
        //requestQueue is used to stack your request and handles your cache.
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
}
