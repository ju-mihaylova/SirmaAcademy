package DIP_05.WeatherReporter;

public class TemperatureSensor implements Sensor {
    @Override
    public double getReading() {
        // Return temperature from sensor
        return 25.0; // dummy value
    }
}
