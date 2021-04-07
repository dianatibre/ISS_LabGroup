package core.service;

import core.domain.Login;
import core.repository.LoginRepoI;
import core.repository.PCMemberRepoI;
import core.repository.ParticipantRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepoI loginRepo;

    @Autowired
    private ParticipantRepoI participantRepo;

    @Autowired
    private PCMemberRepoI pcMemberRepo;

    public Optional<Login> findOneLogin(Integer id) {
        return loginRepo.findById(id);
    }

    public List<Login> getLogins() {
        return new ArrayList<>(this.loginRepo.findAll());
    }

    public boolean addLogin(Login login) {
        if (login.getUsername().equals("") || login.getPassword().equals(""))
            return false;

        List<Login> logins = this.loginRepo.findAll();
        if (logins.contains(login)) {
            return false;
        }
        this.loginRepo.save(login);
        return true;
    }

    public boolean deleteLogin(Integer id) {
        Optional<Login> conf = this.loginRepo.findById(id);
        if (conf.isPresent()) {
            this.loginRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateLogin(Login login) {
        if (login.getUsername().equals("") || login.getPassword().equals(""))
            return false;

        List<Login> logins = this.loginRepo.findAll();
        if (logins.contains(login)) {
            Login toUpdate = logins.get(logins.indexOf(login));
            toUpdate.setPassword(login.getPassword());
            toUpdate.setParticipant(login.getParticipant());
            toUpdate.setPcMember(login.getPcMember());
        }
        return true;
    }
}
