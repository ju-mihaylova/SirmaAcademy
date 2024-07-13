package DIP_05.LightSwitch;

public class LightSwitch {
    private final Switchable switchable;

    public LightSwitch(Switchable switchable) {
        this.switchable = switchable;
    }

    public void operate() {
        // Toggle bulb state
        switchable.turnOn();
    }


}
