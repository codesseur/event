package net.sfr.tv.event;

public interface EventListener<T extends Event> {

  void on(T event);
}
