package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.domain.Email;
import ro.ubb.catalog.core.repository.EmailRepoI;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepoI emailRepoI;

    public List<Email> getAllEmails() {
        return emailRepoI.findAll();
    }

    public boolean sendInvitation(Email email) {
        if (email.getId() < 0 || email.getEmail().equals("") || email.getToken().equals("")) {
            return false;
        }

        if (!email.getEmail().contains("@") || email.getEmail().length() < 3 || (email.getEmail().charAt(0) == '@') || (email.getEmail().charAt(email.getEmail().length() - 1) == '@')) {
            return false;
        }

        Optional<Email> optionalEmail = emailRepoI.findById(email.getId());
        if (optionalEmail.isPresent()) {
            return false;
        }

        this.emailRepoI.save(email);
        return true;
    }
}
