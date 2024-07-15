package com.github.reactive_programming.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Ping {
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }
}
