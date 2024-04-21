package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.services.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/verify")
public class VerificationController {
    private final VerificationService verificationService;

    @GetMapping("/{id}")
    public void verify(@PathVariable("id") String id) {
        verificationService.verify(id);
    }
}
