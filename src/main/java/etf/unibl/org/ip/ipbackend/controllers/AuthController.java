package etf.unibl.org.ip.ipbackend.controllers;


import etf.unibl.org.ip.ipbackend.models.requests.LoginRequest;
import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;
import etf.unibl.org.ip.ipbackend.services.AuthService;
import etf.unibl.org.ip.ipbackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping(path = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@ModelAttribute @Valid RegistrationRequest request) {
        userService.register(request);
    }

    @PostMapping(path = "login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@ModelAttribute @Valid LoginRequest request) {
        authService.login(request);
    }

}
