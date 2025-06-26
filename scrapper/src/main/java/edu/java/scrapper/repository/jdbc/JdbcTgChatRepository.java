package edu.java.scrapper.repository.jdbc;

import edu.java.scrapper.dto.TgChat;
import edu.java.scrapper.repository.TgChatRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class JdbcTgChatRepository implements TgChatRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTgChatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<TgChat> CHAT_ROW_MAPPER = (rs, rowNum) -> new TgChat(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getObject("created_at", OffsetDateTime.class)
    );

    @Override
    public TgChat add(Long id, String username) {
        return jdbcTemplate.queryForObject(
                "INSERT INTO chats (id, username) VALUES (?, ?) RETURNING *",
                CHAT_ROW_MAPPER,
                id, username
        );
    }

    @Override
    public TgChat remove(Long id) {
        TgChat chat = jdbcTemplate.queryForObject(
                "SELECT * FROM chats WHERE id = ?",
                CHAT_ROW_MAPPER,
                id
        );
        jdbcTemplate.update("DELETE FROM chats WHERE id = ?", id);
        return chat;
    }

    @Override
    public List<TgChat> findAll() {
        return jdbcTemplate.query("SELECT * FROM chats", CHAT_ROW_MAPPER);
    }
} 