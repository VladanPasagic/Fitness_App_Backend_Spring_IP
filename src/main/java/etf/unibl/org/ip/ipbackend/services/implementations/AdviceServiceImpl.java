package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.entities.AdviceEntity;
import etf.unibl.org.ip.ipbackend.models.requests.AdviceRequest;
import etf.unibl.org.ip.ipbackend.respositories.AdviceRepository;
import etf.unibl.org.ip.ipbackend.services.AdviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdviceServiceImpl implements AdviceService {
    private final AdviceRepository adviceRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(AdviceRequest advice) {
        adviceRepository.save(createAdviceEntity(advice));
    }

    private AdviceEntity createAdviceEntity(AdviceRequest request) {
        return modelMapper.map(request, AdviceEntity.class);
    }
}
