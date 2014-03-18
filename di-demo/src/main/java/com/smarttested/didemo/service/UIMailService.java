package com.smarttested.didemo.service;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.smarttested.didemo.model.Email;

import java.util.Collections;
import java.util.List;

/**
 * Stub of UI-mail service. Just for demo
 *
 * @author Andrei Varabyeu
 */
public class UIMailService implements MailService {

    public void sendEmail(Email email) {
        /* Do nothing. This is just stub for demo */
    }

    public List<Email> receiveAllEmails() {
        /* Do nothing. This is just demo stub */
        return Collections.emptyList();
    }

    public Optional<Email> receiveEmail(Predicate<Email> predicate) {
        /* Return just absent */
        return Optional.absent();
    }

}
