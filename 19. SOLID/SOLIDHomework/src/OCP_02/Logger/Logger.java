package OCP_02.Logger;

public class Logger {
    private LogHandler logHandler;

    public Logger(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    public void setLog(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    public void log(String message) {
        logHandler.log(message);
    }
}
