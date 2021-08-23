package com.codesseur.dialog;

public interface Responder<T extends Question> {

  Answer ask(T question);
}
