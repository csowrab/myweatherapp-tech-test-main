package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }


  //@GetMapping("timeline/{city}?unitGroup=metric")
  public String DaylightComparison(String first, String second){

    CityInfo firstCity = weatherService.forecastByCity(first);

    CityInfo.CurrentConditions firstCityInfo = firstCity.new CurrentConditions();


    String firstCitySunrise = firstCityInfo.sunrise;
    
    int firstCityRiseHours = Integer.parseInt(firstCitySunrise.substring(0, 2));
    int firstCityRiseMinutes = Integer.parseInt(firstCitySunrise.substring(3,5));

    String firstCitySunset = firstCityInfo.sunset;

    int firstCitySetHours = Integer.parseInt(firstCitySunset.substring(0, 2));
    int firstCitySetMinutes = Integer.parseInt(firstCitySunset.substring(3,5));

    int firstTotalMinutes = (firstCitySetHours*60 + firstCitySetMinutes) - (firstCityRiseHours*60 + firstCityRiseMinutes);
    

    CityInfo secondCity = weatherService.forecastByCity(second);

    CityInfo.CurrentConditions secondCityInfo = secondCity.new CurrentConditions();


    String secondCitySunrise = firstCityInfo.sunrise;
    String secondCitySunset = firstCityInfo.sunset;

    int secondCityRiseHours = Integer.parseInt(secondCitySunrise.substring(0, 2));
    int secondityRiseMinutes = Integer.parseInt( secondCitySunrise.substring(3,5));

    int secondCitySetHours = Integer.parseInt(secondCitySunset.substring(0, 2));
    int secondCitySetMinutes = Integer.parseInt(secondCitySunset.substring(3,5));

    int secondTotalMinutes = (secondCitySetHours*60 + secondCitySetMinutes) - (secondCitySetHours*60 + secondCitySetMinutes);
    
  
    if (firstTotalMinutes > secondTotalMinutes){
      return first;
    }

    return second;

  }
  
  public String RainCheck(String first, String second){

    CityInfo firstCity = weatherService.forecastByCity(first);

    CityInfo.CurrentConditions firstCityInfo = firstCity.new CurrentConditions();

    String firstCityConditions = firstCityInfo.conditions;

    CityInfo secondCity = weatherService.forecastByCity(second);

    CityInfo.CurrentConditions secondCityInfo = secondCity.new CurrentConditions();

    String secondCityConditions = secondCityInfo.conditions;

    if 

}
