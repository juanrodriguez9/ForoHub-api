package com.forohub.forohub_api.controller;

import com.forohub.forohub_api.domain.topic.DataSavedTopic;
import com.forohub.forohub_api.domain.topic.DataTopic;
import com.forohub.forohub_api.domain.topic.DataTopicList;
import com.forohub.forohub_api.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity topicNew(@RequestBody @Valid DataTopic dataNewTopic){
        var responseNewTopic = topicService.addNewTopic(dataNewTopic);

        return ResponseEntity.ok(responseNewTopic);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity topicUpdate(@PathVariable Long id, @RequestBody @Valid DataTopic dataUpdateTopic){
        var updatedTopic = topicService.updateTopic(id, dataUpdateTopic);

        return ResponseEntity.ok(new DataSavedTopic(updatedTopic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity topicDelete(@PathVariable @Valid Long id){
        var deletedTopic = topicService.deleteTopic(id);

        return ResponseEntity.ok(deletedTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DataTopicList>> topicAll(@PageableDefault(size = 10, sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(topicService.listTopic(pageable));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity tipicDetail(@PathVariable @Valid Long id){
        return ResponseEntity.ok(topicService.detailTopic(id));
    }
}
