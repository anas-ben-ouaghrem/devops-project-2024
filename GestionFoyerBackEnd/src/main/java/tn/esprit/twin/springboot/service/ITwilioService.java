package tn.esprit.twin.springboot.service;

public interface ITwilioService {
    public void sendSms(String recipientPhoneNumber, String messageContent);
}
