package com.nure.bearer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;

import com.nure.bearer.Adapter.Cell;
import com.nure.bearer.Adapter.Machine;
import com.nure.bearer.Adapter.MachinsAdapter;
import com.nure.bearer.Helper.RestHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Machins extends AppCompatActivity {
    Thread thread;
    ArrayList<Machine> machine = new ArrayList<>();
    private final OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machins);
        final ListView textView = findViewById(R.id.machinListVie);
        MachinsAdapter machinsAdapter = new MachinsAdapter(this, getAllmachins());
        textView.setAdapter(machinsAdapter);
    }
    public ArrayList<Machine> getAllmachins() {
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String string = "http://10.0.2.2:8080/getMachines";
                    Request request = new Request.Builder()
                            .url(string)
                            .addHeader("Authorization", "Bearer " + RestHelper.KEY)
                            .get()
                            .build();
                    Call call = client.newCall(request);
                    Response response = call.execute();

                    String s = response.body().string();

                    if (response.code() == 200) {

                        WorkWithJson(s);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        synchronized (this) {
            try {
                wait(1000);
            } catch (InterruptedException ie) {
            }
        }
        while (thread.isAlive()) {
        }

        return machine;

    }
    public void WorkWithJson(String s) throws JSONException {
        JSONObject obj = new JSONObject("{machines :" + s + "}");
        machine = new ArrayList<>();
        JSONArray array = obj.getJSONArray("machines");
        for (int i = 0; i < array.length(); i++) {
            Machine machine=new Machine(array.getJSONObject(i).getString("name"),
                    array.getJSONObject(i).getString("location"),
                    WorkWithJsonToCell(array.getJSONObject(i).getString("cellList"))
                    );
            this.machine.add(machine);
        }
    }

    public ArrayList<Cell> WorkWithJsonToCell(String s) throws JSONException {
        JSONObject obj = new JSONObject(  s );
        ArrayList<Cell> cells=new ArrayList<>();
        JSONArray array = obj.getJSONArray("cellList");
        for (int i = 0; i < array.length(); i++) {
     Cell cell=new Cell(
             array.getJSONObject(i).getString("status"),
             array.getJSONObject(i).getString("name"),
             array.getJSONObject(i).getString("tmp"),
             array.getJSONObject(i).getDouble("price"),
             array.getJSONObject(i).getString("shelfLife"),
             array.getJSONObject(i).getString("foodName"),
             array.getJSONObject(i).getString("foodName"),
             array.getJSONObject(i).getInt("number"),
             array.getJSONObject(i).getInt("id"),
             array.getJSONObject(i).getDouble("weight")
             );
            cells.add(cell);
        }
        return  cells;
    }
}