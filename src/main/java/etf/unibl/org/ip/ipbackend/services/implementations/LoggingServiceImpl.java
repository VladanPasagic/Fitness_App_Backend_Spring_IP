package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.entities.LogEntity;
import etf.unibl.org.ip.ipbackend.respositories.LogRepository;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoggingServiceImpl implements LoggingService {
    private final LogRepository logRepository;

    @Override
    public void log(LogLevel level, String message) {
        logRepository.save(new LogEntity(level, message, new Date()));
    }
}
