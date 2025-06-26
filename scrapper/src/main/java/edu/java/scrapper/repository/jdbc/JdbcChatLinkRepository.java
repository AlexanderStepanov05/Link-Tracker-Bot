package edu.java.scrapper.repository.jdbc;

import edu.java.scrapper.repository.ChatLinkRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcChatLinkRepository implements ChatLinkRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcChatLinkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(long chatId, long linkId) {
        jdbcTemplate.update(
                "INSERT INTO chat_links (chat_id, link_id) VALUES (?, ?)",
                chatId, linkId
        );
    }

    @Override
    public void remove(long chatId, long linkId) {
        jdbcTemplate.update(
                "DELETE FROM chat_links WHERE chat_id = ? AND link_id = ?",
                chatId, linkId
        );
    }

    @Override
    public List<Long> findLinksByChat(long chatId) {
        return jdbcTemplate.query(
                "SELECT link_id FROM chat_links WHERE chat_id = ?",
                (rs, rowNum) -> rs.getLong("link_id"),
                chatId
        );
    }

    @Override
    public List<Long> findChatsByLink(long linkId) {
        return jdbcTemplate.query(
                "SELECT chat_id FROM chat_links WHERE link_id = ?",
                (rs, rowNum) -> rs.getLong("chat_id"),
                linkId
        );
    }
} 