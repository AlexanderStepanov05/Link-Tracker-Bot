package edu.java.scrapper.client;

import edu.java.scrapper.dto.GitHubRepoResponse;

public interface GitHubClient {
    GitHubRepoResponse fetchRepo(String owner, String repo);
}
