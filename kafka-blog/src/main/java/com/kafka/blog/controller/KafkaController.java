package com.kafka.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("event")
@AllArgsConstructor
public class KafkaController {
  private StreamBridge streamBridge;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void publishMessage() {
    try {
      streamBridge.send(
          "pub-out-0",
          MessageBuilder.withPayload("Test")
              .setHeader(KafkaHeaders.MESSAGE_KEY, "key")
              .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
              .build());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
