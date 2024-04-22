package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.models.dtos.ChatMessage;
import etf.unibl.org.ip.ipbackend.models.requests.ChatMessageRequest;
import etf.unibl.org.ip.ipbackend.models.requests.ChatRequest;
import etf.unibl.org.ip.ipbackend.services.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/chat")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @PostMapping
    public void sendMessage(@RequestBody ChatMessageRequest request) {
        chatMessageService.sendMessage(request);
    }

    @PostMapping("/{id}")
    public List<ChatMessage> getMessages(@PathVariable("id") String id, @RequestBody ChatRequest request) {
        return chatMessageService.getMessages(Integer.parseInt(id), request.getParticipant());
    }

}
