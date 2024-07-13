package DIP_05.WeatherReporter;

public class WeatherReporter {
    private final Sensor sensor;

    public WeatherReporter(Sensor sensor) {
        this.sensor = sensor;
    }

    public String report() {
        return "Current temperature: " + sensor.getReading();
    }
}
