--liquibase formatted sql
--changeset author:3

CREATE TABLE chat_links
(
    chat_id BIGINT NOT NULL,
    link_id BIGINT NOT NULL,

    PRIMARY KEY (chat_id, link_id),
    FOREIGN KEY (chat_id) REFERENCES chats (id) ON DELETE CASCADE,
    FOREIGN KEY (link_id) REFERENCES links (id) ON DELETE CASCADE
);

COMMENT ON TABLE chat_links IS 'Таблица связи чатов и ссылок';
COMMENT ON COLUMN chat_links.chat_id IS 'Идентификатор чата';
COMMENT ON COLUMN chat_links.link_id IS 'Идентификатор ссылки';
