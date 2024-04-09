package etf.unibl.org.ip.ipbackend.controllers;


import etf.unibl.org.ip.ipbackend.models.requests.EMailRequest;
import etf.unibl.org.ip.ipbackend.services.EMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/send-email")
public class EMailController {
    private final EMailService eMailService;

    @PostMapping
    public void sendEmail(@RequestBody final EMailRequest eMailRequest) {
        eMailService.sendEmail(eMailRequest);
    }
}
