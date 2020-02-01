package com.example.WeatherRestApplication.resource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class WeatherResource {


    @RequestMapping(value="/cities/{cityName}",method = RequestMethod.GET)
    public String getTemperature(@PathVariable String cityName)
    {
        RestTemplate restTemplate = new RestTemplate();

        String uri= "https://openweathermap.org/data/2.5/forecast?q="+cityName+"&mode=Json&appid=b6907d289e10d714a6e88b30761fae22";


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String retrunedString = null;

//        String response = template.getForEntity(uri, String.class).toString();

        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson (restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody(), JsonElement.class).getAsJsonObject();

        final JsonArray data = jsonObj.getAsJsonArray("list");
        System.out.println("data : " + data);
        System.out.println("cod : " + jsonObj.get("cod"));
        List<String> list = new ArrayList<String>();
        for (JsonElement element : data) {

            JsonObject obj1 = ((JsonObject) element).get("main").getAsJsonObject();
            System.out.println(obj1);
            System.out.println(obj1.get("temp"));

            Long temp = obj1.get("temp").getAsLong();

            if(temp > 40l)
            {
                retrunedString = "wear Jacket";
                System.out.println("wear Jacket");
            }
        }
        return retrunedString;
    }

}