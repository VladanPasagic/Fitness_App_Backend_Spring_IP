package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.KgLoss;
import etf.unibl.org.ip.ipbackend.models.entities.KgLossEntity;
import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.requests.KgLossRequest;
import etf.unibl.org.ip.ipbackend.respositories.KgLossRepository;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.KgLossService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KgLossServiceImpl implements KgLossService {
    private final KgLossRepository kgLossRepository;
    private final ModelMapper modelMapper;
    private final TraineeRepository traineeRepository;
    private final LoggingService loggingService;

    @Override
    public List<KgLoss> getAllByUser(int id) {
        return kgLossRepository.getAllByTraineeId(id).stream().map(kl -> modelMapper.map(kl, KgLoss.class)).toList();
    }

    @Override
    public void save(int id, KgLossRequest kgLoss) {
        KgLossEntity kgLossEntity = modelMapper.map(kgLoss, KgLossEntity.class);
        Optional<TraineeEntity> traineeEntity = traineeRepository.findById(id);
        if (traineeEntity.isPresent()) {
            kgLossEntity.setTrainee(traineeEntity.get());
            kgLossEntity.setDate(new Date(System.currentTimeMillis()));
            kgLossRepository.save(kgLossEntity);
        } else {
            loggingService.log(LogLevel.INFO, "Invalid user");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }


}
