package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.requests.EMailRequest;

public interface EMailService {
    void sendEmail(EMailRequest eMailRequest);
}
