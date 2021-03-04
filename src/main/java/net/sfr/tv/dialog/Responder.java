package net.sfr.tv.dialog;

public interface Responder<T extends Question> {

  Answer ask(T question);
}
