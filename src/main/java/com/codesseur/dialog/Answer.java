package com.codesseur.dialog;

import static java.util.Collections.singletonList;
import static java.util.Objects.requireNonNull;

import com.codesseur.iterate.Streamed;
import com.codesseur.iterate.container.Sequence;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Answer implements Sequence<Objection> {

  private final List<Objection> objections;

  public Answer(List<Objection> objections) {
    this.objections = requireNonNull(objections);
  }

  @Override
  public List<Objection> value() {
    return objections;
  }

  public static Answer of(Answer... answers) {
    return of(Arrays.stream(answers));
  }

  public static Answer of(Stream<Answer> answers) {
    return Streamed.of(answers).flatMap(a -> a).toListThen(Answer::new);
  }

  public static Answer approve() {
    return new Answer(Collections.emptyList());
  }

  public static Answer refuse(RuntimeException e) {
    return new Answer(singletonList(new Objection(e)));
  }

  List<Objection> objections() {
    return value();
  }

  public void onObjection(Consumer<Objection> consumer) {
    objections().stream().findFirst().ifPresent(consumer);
  }
}
