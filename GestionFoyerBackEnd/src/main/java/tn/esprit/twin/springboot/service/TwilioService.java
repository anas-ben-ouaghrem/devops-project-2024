package tn.esprit.twin.springboot.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService implements ITwilioService{

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    public void sendSms(String destinataire, String message) {
        Twilio.init(accountSid, authToken);

        Message.creator(
                        new PhoneNumber(destinataire),
                        new PhoneNumber(phoneNumber),
                        message)
                .create();
    }
}
