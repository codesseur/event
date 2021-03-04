package net.sfr.tv.dialog;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.immutables.value.Value.Immutable;

@Immutable
public abstract class Answer {

  public static Answer of(Answer... answers) {
    return of(Arrays.stream(answers));
  }

  public static Answer of(Stream<Answer> answers) {
    return answers.flatMap(answer -> answer.objections().stream())
        .collect(collectingAndThen(toList(), ImmutableAnswer::of));
  }

  public static Answer approve() {
    return ImmutableAnswer.builder().objections(Collections.emptyList()).build();
  }

  public static Answer refuse(RuntimeException e) {
    return ImmutableAnswer.builder().addObjection(ImmutableObjection.of(e)).build();
  }

  abstract List<Objection> objections();

  public void onObjection(Consumer<Objection> consumer) {
    objections().stream().findFirst().ifPresent(consumer);
  }
}
