package net.sfr.tv;

import net.sfr.tv.dialog.Answer;
import net.sfr.tv.dialog.Audience;
import net.sfr.tv.dialog.Question;
import net.sfr.tv.dialog.Responder;
import net.sfr.tv.event.Event;
import net.sfr.tv.event.EventDispatcher;
import net.sfr.tv.event.EventListener;

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

  public void register(EventListener<? extends Event> listener) {
    eventDispatcher.register(listener);
  }

  public void register(Responder<? extends Question> responder) {
    audience.register(responder);
  }

}
