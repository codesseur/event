package com.codesseur.dialog;

import com.codesseur.Universe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AudienceTest {

  @BeforeEach
  void setUp() {
    Universe.get().clear();
  }

  @Test
  void askWithApproval() {
    Responder<Question> responder = q -> Answer.approve();
    Universe.get().register(responder);

    Answer answer = Universe.get().ask(new Question() {});

    Assertions.assertThat(answer.objections()).isEmpty();
  }

  @Test
  void askWithRefusal() {
    Responder<Question> responder = q -> Answer.refuse(new RuntimeException("no no!"));
    Universe.get().register(responder);

    Answer answer = Universe.get().ask(new Question() {});

    Assertions.assertThat(answer.objections()).hasSize(1);
  }
}