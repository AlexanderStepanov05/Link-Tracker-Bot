package edu.java.scrapper.repository.jdbc;

import edu.java.scrapper.dto.Link;
import edu.java.scrapper.repository.LinkRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class JdbcLinkRepository implements LinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcLinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Link> LINK_ROW_MAPPER = (rs, rowNum) -> new Link(
            rs.getLong("id"),
            URI.create(rs.getString("url")),
            rs.getObject("last_checked_at", OffsetDateTime.class),
            rs.getObject("created_at", OffsetDateTime.class)
    );

    @Override
    public Link add(URI url) {
        return jdbcTemplate.queryForObject(
                "INSERT INTO links (url) VALUES (?) RETURNING *",
                LINK_ROW_MAPPER,
                url.toString()
        );
    }

    @Override
    public Link remove(URI url) {
        Link link = jdbcTemplate.queryForObject(
                "SELECT * FROM links WHERE url = ?",
                LINK_ROW_MAPPER,
                url.toString()
        );
        jdbcTemplate.update("DELETE FROM links WHERE url = ?", url.toString());
        return link;
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("SELECT * FROM links", LINK_ROW_MAPPER);
    }

    @Override
    public List<Link> findNotCheckedSince(OffsetDateTime threshold) {
        return jdbcTemplate.query(
                "SELECT * FROM links WHERE last_checked_at < ?",
                LINK_ROW_MAPPER,
                threshold
        );
    }
} 