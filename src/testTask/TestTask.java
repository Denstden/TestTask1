package testTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import testTask.domain.City;
import testTask.domain.Country;
import testTask.service.CityService;
import testTask.service.CountryService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTask {

    public void solve() throws JSONException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        CountryService countryService = applicationContext.getBean("countryServiceImpl", CountryService.class);
        CityService cityService = applicationContext.getBean("cityServiceImpl", CityService.class);
        for(Map.Entry<Country,List<City>> entry:responseToMap(getResponse()).entrySet()){
            System.out.println(countryService.createCountry(entry.getKey()));
            for (City city: entry.getValue()){
                System.out.println(cityService.createCity(city));
            }
        }
    }

    private String createRequest() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","$1$12309856$euBrWcjT767K2sP9MHcVS/");
        jsonObject.put("echo", "test1234");
        return jsonObject.toString();
    }

    private String getResponse(){
        String urlStr = "http://tripcomposer.net/rest/test/countries/get";
        HttpURLConnection urlConn = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.connect();

            DataOutputStream output = new DataOutputStream(urlConn.getOutputStream());
            output.writeBytes(createRequest());
            output.flush();
            output.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine).append('\n');
            }
            in.close();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        return response.toString();
    }

    private Map<Country,List<City>> responseToMap(String response) throws JSONException {
        System.out.println(response);
        JSONObject jsonObject = new JSONObject(response);
        JSONArray countries = (JSONArray)jsonObject.get("countries");
        Map<Country,List<City>> result = new HashMap<>();

        for(int i=0; i<countries.length(); i++){
            Country country = new Country(countries.getJSONObject(i).getString("countryName"),countries.getJSONObject(i).getString("countryISOCode"));
            JSONArray cities1 = countries.getJSONObject(i).getJSONArray("cities");
            ArrayList<City> cities = new ArrayList<>();
            for(int j=0;j<cities1.length();j++) {
                cities.add(new City(cities1.getJSONObject(j).getString("cityName"), country));
            }
            result.put(country, cities);
        }

        return result;
    }
}
