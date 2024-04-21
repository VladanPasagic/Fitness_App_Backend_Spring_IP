package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.Journal;
import etf.unibl.org.ip.ipbackend.models.dtos.KgLoss;
import etf.unibl.org.ip.ipbackend.models.requests.JournalRequest;
import etf.unibl.org.ip.ipbackend.models.requests.KgLossRequest;
import etf.unibl.org.ip.ipbackend.services.JournalService;
import etf.unibl.org.ip.ipbackend.services.KgLossService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/journals")
public class JournalController {
    private final JournalService journalService;
    private final KgLossService kgLossService;

    @GetMapping("/{id}")
    public List<Journal> getJournalById(@PathVariable int id) {
        return journalService.getAllFromUser(id);
    }

    @PostMapping("/{id}")
    public void createJournal(@PathVariable int id, @RequestBody JournalRequest journal) {
        journalService.save(id, journal);
    }

    @GetMapping("/{id}/kg")
    public List<KgLoss> getKgLoss(@PathVariable int id) {
        return kgLossService.getAllByUser(id);
    }

    @PostMapping("/{id}/kg")
    public void createKgLoss(@PathVariable int id, @RequestBody @Valid KgLossRequest kgLoss) {
        kgLossService.save(id, kgLoss);
    }

}
