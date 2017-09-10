package edu.hw.phunware.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WeatherData {
	
	private String zipCode, cityName, forecast;
	private String lowTemp, highTemp;
	
	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}

	public WeatherData() {
		
	}
	
	public WeatherData(String zipCode, String cityName, String lowTemp, String highTemp, String forecast) {
		this.zipCode = zipCode;
		this.cityName = cityName;
		this.lowTemp = lowTemp;
		this.highTemp = highTemp;
		this.forecast = forecast;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}

	public String getLowTemp() {
		return lowTemp;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

}
