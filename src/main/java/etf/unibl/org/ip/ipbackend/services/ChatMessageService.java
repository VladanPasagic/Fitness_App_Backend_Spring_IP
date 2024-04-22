package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.ChatMessage;
import etf.unibl.org.ip.ipbackend.models.requests.ChatMessageRequest;

import java.util.List;

public interface ChatMessageService {
    void sendMessage(ChatMessageRequest chatMessageRequest);

    List<ChatMessage> getMessages(int sender, int receiver);
}
