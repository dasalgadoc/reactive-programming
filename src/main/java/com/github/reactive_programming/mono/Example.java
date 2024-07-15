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

  @GetMapping("/mono/example/1")
  public String exampleJustAndSubscribe() {
    List<String> message = new ArrayList<>();

    // Mono just: Creates a new Mono that emits the specified item
    Mono<String> mono = Mono.just("Hello, world!");
    mono.subscribe(message::add);

    return message.toString();
  }

  @GetMapping("/mono/example/2")
  public String exampleMonoWithThreeParameters() {
    List<String> message = new ArrayList<>();

    Mono<String> mono = Mono.just("Hello, world Example 2!");

    // Mono subscribe with three parameters
    // 1. onNext: Called when a value is emitted by the Mono
    // 2. onError: Called when an error occurs
    // 3. onComplete: Called when the Mono completes without emitting a value
    mono.subscribe(message::add, Throwable::printStackTrace, () -> message.add("Completed"));

    return message.toString();
  }

  @GetMapping("/mono/example/3")
  public String exampleMonoAndError() {
    List<String> message = new ArrayList<>();

    Mono<String> mono =
        Mono.fromSupplier(
            () -> {
              throw new RuntimeException("Error on Mono Example 3!");
            });

    mono.subscribe(
        message::add, error -> message.add(error.getMessage()), () -> message.add("Completed"));

    return message.toString();
  }
}
