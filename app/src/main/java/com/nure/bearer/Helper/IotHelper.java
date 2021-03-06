package com.nure.bearer.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IotHelper {
    public static ArrayList<String> iots;
    public static Map<Integer, Integer> map;
    Thread thread;
    OkHttpClient client = new OkHttpClient();

    public ArrayList<String> getAreas() {
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String json = "{\n" +
                            "    \"id\":" + RestHelper.idIot +
                            "}";
                    RequestBody formBody = RequestBody.create(
                            MediaType.parse("application/json"), json);

                    String string = RestHelper.STANDARTWAY + "/user/getallIots";
                    Request request = new Request.Builder()
                            .url(string)
                            .addHeader("Authorization", "Bearer " + RestHelper.KEY)
                            .post(formBody)
                            .build();
                    Call call = client.newCall(request);
                    Response response = call.execute();

                    String s = response.body().string();
                    if (response.code() == 200) {
                        jsonParse(s);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        while (thread.isAlive()) {
            //)))))))))))
        }
        return iots;

    }

    public void jsonParse(String s) throws JSONException {

        s = "{data:" + s + "}";
        s = s.replace("\"result\":true,", "");
        JSONObject obj = new JSONObject(s);
        System.out.println(obj.toString());
        JSONArray array = obj.getJSONArray("data");
        iots = new ArrayList<String>();
        for (int i = 0; i < array.length(); i++) {
            String string = "";
            string += (array.getJSONObject(i).getInt("id")) + " ";
            string += (array.getJSONObject(i).getInt("xCoordinate")) + " ";
            string += (array.getJSONObject(i).getInt("yCoordinate")) + "\n";
            string += (array.getJSONObject(i).getString("description"));
            iots.add(string);
        }
    }

}