# phunware-weatherAPI
Phunware assignment

A weather forecast web service which provides weather detials like high tempature and low temperature for the given zipcode.

**Web service endpoint: localhost:8080/weather-api/app/weather?zipcode=73301**

The web service takes zipcode or zipcodes(comma separated) as query parameter and returns the response in JSON format.

**Sample request:**
```localhost:8080/weather-api/app/weather?zipcode=73301,75011, 12345```

**Sample Response:**
```
{
  "result": [
    {
      "Forecast": "Sunny",
      "Zipcode": "73301",
      "Low Temp": "61",
      "CityName": "Austin",
      "High Temp": "85",
      "status": true
    },
    {
      "Forecast": "Sunny",
      "Zipcode": "75011",
      "Low Temp": "64",
      "CityName": "Carrollton",
      "High Temp": "84",
      "status": true
    },
    {
      "error": "Please enter a valid zipcode",
      "status": false
    }
  ]
}
```
