package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Advice;
import etf.unibl.org.ip.ipbackend.respositories.AdviceRepository;
import etf.unibl.org.ip.ipbackend.services.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdviceServiceImpl implements AdviceService {
    private final AdviceRepository adviceRepository;

    @Override
    public void save(Advice advice) {
        adviceRepository.save(advice);
    }
}
