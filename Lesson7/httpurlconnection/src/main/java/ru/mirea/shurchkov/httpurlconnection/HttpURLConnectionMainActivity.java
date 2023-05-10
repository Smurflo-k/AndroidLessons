package ru.mirea.shurchkov.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.shurchkov.httpurlconnection.databinding.ActivityHttpUrlconnectionMainBinding;

public class HttpURLConnectionMainActivity extends AppCompatActivity {
    private String latitude;
    private String longitude;
    ActivityHttpUrlconnectionMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHttpUrlconnectionMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkinfo != null && networkinfo.isConnected()) {

                    new DownloadPageTask().execute("https://ipinfo.io/json");

                } else {
                    Toast.makeText(getApplicationContext(), "Нет интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textViewIp.setText("Загружаем...");
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {;
            Log.d(HttpURLConnectionMainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(HttpURLConnectionMainActivity.class.getSimpleName(), "Response: " + responseJson);
                String ip = responseJson.getString("ip");
                binding.textViewIp.setText("IP: "+ ip);
                String city = responseJson.getString("city");
                binding.textViewCity.setText("Город: "+ city);
                String region = responseJson.getString("region");
                binding.textViewRegion.setText("Регион: "+ region);
                String country = responseJson.getString("country");
                binding.textViewCountry.setText("Страна: "+ country);
                String loc = responseJson.getString("loc");
                binding.textViewLocation.setText("Местоположение: "+ loc);
                String org = responseJson.getString("org");
                binding.textViewOrg.setText("Интернет провайдер: "+ org);
                String postal = responseJson.getString("postal");
                binding.textViewPostal.setText("Почтовый индекс: "+ postal);
                String timezone = responseJson.getString("timezone");
                binding.textViewTimezone.setText("Часовой пояс: "+ timezone);
                Log.d(HttpURLConnectionMainActivity.class.getSimpleName(), "IP: " + ip);
                latitude = loc.substring(0,loc.indexOf(","));
                String message1 = "Значение широты: " + latitude;
                Toast.makeText(getApplicationContext(), message1, Toast.LENGTH_SHORT).show();
                longitude = loc.substring(loc.indexOf(",")+1);
                String message2 = "Значение долготы: " + longitude;
                Toast.makeText(getApplicationContext(), message2, Toast.LENGTH_SHORT).show();
                new DownloadWeatherTask().execute("https://api.open-meteo.com/v1/forecast?latitude="+latitude+"&longitude="+longitude+"&current_weather=true");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read); }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage()+". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }
        @Override
        protected void onPostExecute(String result) {;
            Log.d(HttpURLConnectionMainActivity.class.getSimpleName(), result);
            try {
                JSONObject responseJson = new JSONObject(result);
                Log.d(HttpURLConnectionMainActivity.class.getSimpleName(), "Response: " + responseJson);
                JSONObject weatherJson = responseJson.getJSONObject("current_weather");
                String temperature = weatherJson.getString("temperature");
                binding.textViewTemp.setText("Температура за окном: "+ temperature);
                String windspeed = weatherJson.getString("windspeed");
                binding.textViewWind.setText("Скорость ветра сейчас достигает: " + windspeed +"м/с");
                String winddirection = weatherJson.getString("winddirection");
                binding.textViewWindDir.setText("Направление ветра сейчас: " + winddirection + "градуса");
                String is_day = weatherJson.getString("is_day");
                binding.textViewDay.setText("Относится ли запрос к существующему дневному архиву: " + is_day);
                String time = weatherJson.getString("time");
                binding.textViewTime.setText("Время: " + time);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(result);
        }
    }
}
