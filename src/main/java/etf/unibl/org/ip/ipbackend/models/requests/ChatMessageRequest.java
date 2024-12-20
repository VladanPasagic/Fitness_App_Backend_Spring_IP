package etf.unibl.org.ip.ipbackend.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {
    private int sender;
    private int receiver;
    private String message;
}
