package com.codesseur.event;

public interface EventListener<T extends Event> {

  void on(T event);
}
