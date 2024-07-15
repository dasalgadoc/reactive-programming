package com.github.reactive_programming.mono;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("MonoExample")
@RequestMapping
public class Example {

  @GetMapping("/mono/example")
  public String example() {
    List<String> message = new ArrayList<>();

    Mono<String> mono = Mono.just("Hello, world!");
    mono.subscribe(message::add);

    return message.toString();
  }
}
