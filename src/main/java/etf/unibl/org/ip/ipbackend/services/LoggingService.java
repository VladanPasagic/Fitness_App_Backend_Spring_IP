package etf.unibl.org.ip.ipbackend.services;

import org.springframework.boot.logging.LogLevel;

public interface LoggingService {
    public void log(LogLevel level, String message);
}
