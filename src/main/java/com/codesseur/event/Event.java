package com.codesseur.event;

import com.codesseur.iterate.Streamed;

public interface Event {

  default Streamed<Event> in(Streamed<Event> in) {
    return in.append(this);
  }
}
