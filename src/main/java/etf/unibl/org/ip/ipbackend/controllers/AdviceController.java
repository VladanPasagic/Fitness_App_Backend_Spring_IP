package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.requests.AdviceRequest;
import etf.unibl.org.ip.ipbackend.services.AdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/advices")
public class AdviceController {
    private final AdviceService adviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void advice(@RequestBody AdviceRequest advice)
    {
        adviceService.save(advice);
    }
}
