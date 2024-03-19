package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.FitFact;
import etf.unibl.org.ip.ipbackend.services.FitFactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}")
public class FitFactsController {
    private final FitFactsService factsService;

    @GetMapping("facts")
    public List<FitFact> getAllFitFacts() {
        return factsService.getAllFitFacts();
    }
}
