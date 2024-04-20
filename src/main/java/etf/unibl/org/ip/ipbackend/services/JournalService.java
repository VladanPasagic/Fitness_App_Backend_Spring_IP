package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Journal;
import etf.unibl.org.ip.ipbackend.models.requests.JournalRequest;

import java.util.List;

public interface JournalService {
    List<Journal> getAllFromUser(int id);

    void save(int userId, JournalRequest journal);
}
