package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.ChatMessage;
import etf.unibl.org.ip.ipbackend.models.entities.ChatMessageEntity;
import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.requests.ChatMessageRequest;
import etf.unibl.org.ip.ipbackend.respositories.ChatMessageRepository;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.ChatMessageService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final TraineeRepository traineeRepository;
    private final LoggingService loggingService;
    private final ModelMapper modelMapper;

    @Override
    public void sendMessage(ChatMessageRequest chatMessageRequest) {
        ChatMessageEntity chatMessageEntity = new ChatMessageEntity();
        chatMessageEntity.setMessage(chatMessageRequest.getMessage());
        Optional<TraineeEntity> sender = traineeRepository.findById(chatMessageRequest.getSender());
        Optional<TraineeEntity> receiver = traineeRepository.findById(chatMessageRequest.getReceiver());
        if (sender.isPresent() && receiver.isPresent()) {
            chatMessageEntity.setSender(sender.get());
            chatMessageEntity.setRecipient(receiver.get());
            chatMessageEntity.setDate(new Date(System.currentTimeMillis()));
            chatMessageRepository.save(chatMessageEntity);
        } else {
            loggingService.log(LogLevel.INFO, "Couldn't find user");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    @Override
    public List<ChatMessage> getMessages(int sender, int receiver) {
        Stream<ChatMessage> messages1 = chatMessageRepository.getAllBySenderIdAndRecipientIdOrderByDateAsc(sender, receiver)
                .stream().map(m -> modelMapper.map(m, ChatMessage.class));
        Stream<ChatMessage> messages2 = chatMessageRepository.getAllBySenderIdAndRecipientIdOrderByDateAsc(receiver, sender)
                .stream().map(m -> modelMapper.map(m, ChatMessage.class));
        if (sender != receiver) {
            return Stream.concat(messages1, messages2).sorted(Comparator.comparing(ChatMessage::getDate)).toList();
        } else {
            return messages1.sorted(Comparator.comparing(ChatMessage::getDate)).toList();
        }
    }
}
