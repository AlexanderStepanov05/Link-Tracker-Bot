--liquibase formatted sql
--changeset author:2

CREATE TABLE links
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY,
    url           TEXT                     NOT NULL,
    last_checked_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id),
    UNIQUE (url)
);

COMMENT ON TABLE links IS 'Таблица для хранения отслеживаемых ссылок';
COMMENT ON COLUMN links.url IS 'URL ссылки';
COMMENT ON COLUMN links.last_checked_at IS 'Время последней проверки ссылки';
COMMENT ON COLUMN links.created_at IS 'Дата добавления ссылки';
