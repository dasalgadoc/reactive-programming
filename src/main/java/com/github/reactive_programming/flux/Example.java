package com.github.reactive_programming.flux;

import com.github.reactive_programming.flux.application.Pair;
import com.github.reactive_programming.flux.application.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController("FluxExample")
@RequestMapping
public class Example {

  @GetMapping("/flux/example/1")
  public String exampleJustAndSubscribe() {
    List<Integer> numbers = new ArrayList<>();

    Flux<Integer> flux = Flux.just(1, 1, 2, 3, 5, 8, 13, 21, 34);
    flux.subscribe(numbers::add);

    return numbers.toString();
  }

  @GetMapping("/flux/example/2")
  public String exampleGenerateAnError() {
    List<Integer> numbers = new ArrayList<>();

    Flux<Integer> flux =
        Flux.just(1, 2, 3, 4, 5)
            .handle(
                (number, sink) -> {
                  if (number == 3) {
                    sink.error(new RuntimeException("Error on Flux Example 2!"));
                    return;
                  }
                  sink.next(number);
                });

    flux.subscribe(numbers::add, error -> numbers.add(-1));

    return numbers.toString();
  }

  @GetMapping("/flux/example/3")
  public String exampleFluxFromArray() {
    List<Integer> numbers = new ArrayList<>();

    Integer[] array = {1, 2, 3, 4, 5};
    Flux<Integer> flux = Flux.fromArray(array);

    flux.subscribe(numbers::add);

    return numbers.toString();
  }

  @GetMapping("/flux/example/4")
  public String exampleWithRecords(
      @RequestParam(value = "department", defaultValue = "") String department) {
    List<Pair<String, Integer>> employees = new ArrayList<>();

    employees.add(new Pair<>("Alice Abernathy", 25));
    employees.add(new Pair<>("Bob Dylan", 30));
    employees.add(new Pair<>("Charlie Brown", 35));
    employees.add(new Pair<>("Dave TheBarbarian", 40));
    employees.add(new Pair<>("Eve Parasite", 45));

    Flux<Pair<String, Integer>> flux = Flux.fromIterable(employees);
    Flux<User> userFlux =
        flux.map(
            pair ->
                new User(
                    pair.key().split(" ")[0],
                    pair.key().split(" ")[1],
                    department.isEmpty() ? "IT" : department,
                    pair.value()));

    List<User> users = new ArrayList<>();
    userFlux.subscribe(users::add);

    return users.toString();
  }
}
