package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.services.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/journals")
public class JournalController {
    private final JournalService journalService;

}
