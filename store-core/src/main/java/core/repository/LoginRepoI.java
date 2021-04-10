package core.repository;

import core.domain.Login;

public interface LoginRepoI extends RepoI<Login, Integer> {
    public Login findByUsername(String username);
}
