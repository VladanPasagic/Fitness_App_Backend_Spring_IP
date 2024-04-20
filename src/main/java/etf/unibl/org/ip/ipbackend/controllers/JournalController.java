package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.Journal;
import etf.unibl.org.ip.ipbackend.models.requests.JournalRequest;
import etf.unibl.org.ip.ipbackend.services.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/journals")
public class JournalController {
    private final JournalService journalService;

    @GetMapping("/{id}")
    public List<Journal> getJournalById(@PathVariable int id) {
        return journalService.getAllFromUser(id);
    }

    @PostMapping("/{id}")
    public void createJournal(@PathVariable int id, @RequestBody JournalRequest journal) {
        journalService.save(id, journal);
    }

}
