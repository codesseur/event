package net.sfr.tv.dialog;

import org.immutables.value.Value.Immutable;

@Immutable
public interface Objection {

  RuntimeException exception();

  default void throwException() {
    throw exception();
  }

}
