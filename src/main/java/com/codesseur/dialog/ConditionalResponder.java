package com.codesseur.dialog;

import com.codesseur.Universe;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalResponder<T extends Question> implements Responder<T> {

  private final Predicate<Question> matcher;
  private final Responder<T> consumer;

  public ConditionalResponder(Predicate<Question> matcher, Responder<T> consumer) {
    this.matcher = matcher;
    this.consumer = consumer;
  }

  public static <T extends Question> Builder<T> forType(Class<T> type) {
    return matching(type::isInstance);
  }

  public static <T extends Question> Builder<T> matching(Predicate<Question> matcher) {
    return new Builder<>(matcher);
  }

  @Override
  public Answer ask(T question) {
    if (match(question)) {
      return consumer.ask(question);
    } else {
      return Answer.approve();
    }
  }

  private boolean match(Question question) {
    return matcher.test(question);
  }

  public static class Builder<T extends Question> {

    private final Predicate<Question> matcher;
    private Responder<T> responder;

    public Builder(Predicate<Question> matcher) {
      this.matcher = matcher;
    }

    public Builder<T> respond(Function<T, Answer> answerFactory) {
      responder = new ConditionalResponder<>(matcher, answerFactory::apply);
      return this;
    }

    public void register(Universe universe) {
      universe.register(responder);
    }

  }
}
