package OCP_02.Logger;

public class ConsoleLogHandler implements LogHandler {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
