package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.KgLoss;
import etf.unibl.org.ip.ipbackend.models.requests.KgLossRequest;

import java.util.List;

public interface KgLossService {
    List<KgLoss> getAllByUser(int id);

    void save(int id, KgLossRequest kgLoss);
}
