package com.forohub.forohub_api.domain.topic;

import java.time.LocalDateTime;

public record DataTopicList(
        String title,
        String message,
        LocalDateTime creationDate,
        Boolean status,
        String author,
        String course
) {
    public DataTopicList(Topic topic){
        this(
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatus(),
                topic.getAuthor().getName(),
                topic.getCourse().getName()
        );
    }
}
