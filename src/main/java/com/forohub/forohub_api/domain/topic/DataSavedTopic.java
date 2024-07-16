package com.forohub.forohub_api.domain.topic;

import java.time.LocalDateTime;

public record DataSavedTopic(Long id,
                             String title,
                             String message,
                             LocalDateTime createdDate
) {
    public DataSavedTopic(Topic topic){
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate()
        );
    }
}
