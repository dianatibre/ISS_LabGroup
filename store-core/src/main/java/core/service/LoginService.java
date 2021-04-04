package core.service;

import core.domain.ExtendedLogin;
import core.domain.Login;
import core.domain.Participant;
import core.repository.LoginRepoI;
import core.repository.ParticipantRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private LoginRepoI repo;

    @Autowired
    private ParticipantRepoI partRepo;

    public boolean signUp(Login login) {
        Optional<Login> lo = repo.findAll().stream().filter(l -> l.getUsername().equals(login.getUsername())).findAny();
        if (lo.isPresent())
            return false;
        repo.save(login);
        return true;
    }

    public List<ExtendedLogin> login(String username, String password) {
        Optional<Login> lo = repo.findAll().stream().filter(l -> l.getUsername().equals(username) && l.getPassword().equals(password)).findAny();
        if (lo.isPresent()) {
            List<ExtendedLogin> result = new ArrayList<>();

            List<Participant> parts = partRepo.findAll().stream().filter(p -> p.getLogin().equals(lo.get())).collect(Collectors.toList());
            for (Participant part : parts) {
                result.add(new ExtendedLogin(part.getClass().getSimpleName(), part));
            }

            return result;
        } else {
            return new ArrayList<>();
        }
    }
}
