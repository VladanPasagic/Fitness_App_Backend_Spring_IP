package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;
import etf.unibl.org.ip.ipbackend.models.requests.ProfileUpdateRequest;
import etf.unibl.org.ip.ipbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/profile")
public class UserProfileController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Profile getUserProfile(@PathVariable String userId) {
        return userService.getProfile(Integer.parseInt(userId));
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUserProfile(@PathVariable String userId, @ModelAttribute ProfileUpdateRequest profile) throws IOException {
        userService.updateProfile(Integer.parseInt(userId), profile);
    }
}
