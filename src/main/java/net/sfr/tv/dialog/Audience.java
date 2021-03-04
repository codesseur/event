package net.sfr.tv.dialog;

import java.util.ArrayList;
import java.util.List;

public class Audience {

  private final List<Responder<Question>> responders = new ArrayList<>();

  public Answer ask(Question question) {
    return Answer.of(responders.stream().map(r -> r.ask(question)));
  }

  public void register(Responder<? extends Question> responder) {
    responders.add((Responder<Question>) responder);
  }
}
