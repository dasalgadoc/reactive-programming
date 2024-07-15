package com.github.reactive_programming.flux;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController("FluxExample")
@RequestMapping
public class Example {

  @GetMapping("/flux/example")
  public String example() {
    List<Integer> numbers = new ArrayList<>();

    Flux<Integer> flux = Flux.just(1, 1, 2, 3, 5, 8, 13, 21, 34);

    flux.subscribe(numbers::add);
    return numbers.toString();
  }
}
