package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Journal;
import etf.unibl.org.ip.ipbackend.models.entities.JournalEntity;
import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import etf.unibl.org.ip.ipbackend.models.requests.JournalRequest;
import etf.unibl.org.ip.ipbackend.respositories.JournalRepository;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.JournalService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;
    private final TraineeRepository traineeRepository;
    private final ModelMapper modelMapper;
    private final LoggingService loggingService;


    @Override
    public List<Journal> getAllFromUser(int id) {
        return journalRepository.getAllByTraineeId(id).stream().map(j -> modelMapper.map(j, Journal.class)).toList();
    }

    @Override
    public void save(int userId, JournalRequest journal) {
        Optional<TraineeEntity> traineeEntity = traineeRepository.findById(userId);
        JournalEntity journalEntity = modelMapper.map(journal, JournalEntity.class);
        switch (journal.getIntensity()) {
            case 0:
                journalEntity.setIntensity(ProgramLevel.Beginner);
                break;
            case 1:
                journalEntity.setIntensity(ProgramLevel.Intermediate);
                break;
            case 2:
                journalEntity.setIntensity(ProgramLevel.Hard);
                break;
            default:
                throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
        if (traineeEntity.isPresent()) {
            journalEntity.setTrainee(traineeEntity.get());
        } else {
            loggingService.log(LogLevel.WARN, "User with id " + userId + " not found");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        journalRepository.save(journalEntity);
    }
}
