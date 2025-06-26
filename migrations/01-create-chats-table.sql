--liquibase formatted sql
--changeset author:1

CREATE TABLE chats
(
    id         BIGINT                   NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    username   TEXT                     NOT NULL,

    PRIMARY KEY (id)
);

COMMENT ON TABLE chats IS 'Таблица для хранения чатов пользователей';
COMMENT ON COLUMN chats.id IS 'Идентификатор чата в Telegram';
COMMENT ON COLUMN chats.created_at IS 'Дата создания записи';
COMMENT ON COLUMN chats.username IS 'Имя пользователя в Telegram';
