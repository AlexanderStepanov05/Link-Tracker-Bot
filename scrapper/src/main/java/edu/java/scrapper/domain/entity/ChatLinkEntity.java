package edu.java.scrapper.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_links")
public class ChatLinkEntity {
    @EmbeddedId
    private ChatLinkId id;

    @Embeddable
    public static class ChatLinkId implements java.io.Serializable {
        @Column(name = "chat_id")
        private Long chatId;
        @Column(name = "link_id")
        private Long linkId;

        public ChatLinkId() {}
        public ChatLinkId(Long chatId, Long linkId) {
            this.chatId = chatId;
            this.linkId = linkId;
        }
        public Long getChatId() { return chatId; }
        public void setChatId(Long chatId) { this.chatId = chatId; }
        public Long getLinkId() { return linkId; }
        public void setLinkId(Long linkId) { this.linkId = linkId; }
    }

    public ChatLinkId getId() { return id; }
    public void setId(ChatLinkId id) { this.id = id; }
} 