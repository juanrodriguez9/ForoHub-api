package com.forohub.forohub_api.domain.topic;

import com.forohub.forohub_api.domain.course.Course;
import com.forohub.forohub_api.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Column(unique = true)
    private String message;
    private LocalDateTime creationDate;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    private Integer answers;

    public Topic(String title, String message, LocalDateTime creationDate, UserEntity userEntity, Course course){
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.author = userEntity;
        this.course = course;
        this.answers = 0;
        this.status = false;
    }

    public void updateTopic(DataTopic dataTopic, Course course){
        if(dataTopic.title() != null){
            this.title = dataTopic.title();
        }

        if(dataTopic.message() != null){
            this.message = dataTopic.message();
        }

        if(course != null){
            this.course = course;
        }
    }

}
