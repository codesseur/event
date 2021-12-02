package com.codesseur;

import com.codesseur.dialog.Answer;
import com.codesseur.dialog.Audience;
import com.codesseur.dialog.Question;
import com.codesseur.dialog.Responder;
import com.codesseur.dialog.ResponderSubscription;
import com.codesseur.event.Event;
import com.codesseur.event.EventDispatcher;
import com.codesseur.event.EventListener;
import com.codesseur.event.ListenerSubscription;

public class Universe implements Responder<Question> {

  private final EventDispatcher eventDispatcher;
  private final Audience audience;
  private static final Universe INSTANCE = new Universe();

  public static Universe get() {
    return INSTANCE;
  }

  public Universe() {
    this.eventDispatcher = new EventDispatcher();
    this.audience = new Audience();
  }

  @Override
  public Answer ask(Question question) {
    return audience.ask(question);
  }

  public void post(Event event) {
    eventDispatcher.send(event);
  }

  public void post(Iterable<? extends Event> event) {
    event.forEach(this::post);
  }

  public ListenerSubscription register(EventListener<? extends Event> listener) {
    return eventDispatcher.register(listener);
  }

  public ResponderSubscription register(Responder<? extends Question> responder) {
    return audience.register(responder);
  }

  public void clear() {
    audience.clear();
    eventDispatcher.clear();
  }

}
