package edu.java.scrapper.repository;

import edu.java.scrapper.dto.Link;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

public interface LinkRepository {
    Link add(URI url);
    Link remove(URI url);
    List<Link> findAll();
    List<Link> findNotCheckedSince(OffsetDateTime threshold);
} 