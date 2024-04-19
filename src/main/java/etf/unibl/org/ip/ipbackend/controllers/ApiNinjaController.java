package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.Exercise;
import etf.unibl.org.ip.ipbackend.services.ApiNinjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/api-ninja")
public class ApiNinjaController {
    private final ApiNinjaService apiNinjaService;

    @GetMapping
    public List<Exercise> getExercises() {
        return apiNinjaService.getAll();
    }
}
