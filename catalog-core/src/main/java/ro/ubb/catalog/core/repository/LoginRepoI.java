package ro.ubb.catalog.core.repository;


import ro.ubb.catalog.core.domain.Login;

public interface LoginRepoI extends RepoI<Login, Integer> {
    public Login findByUsername(String username);
}
