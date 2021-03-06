package serialization.manager.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.*;

import serialization.manager.service.*;

public class SerializationManagerFactoryTest {

  private SerializationManagerFactory serializationManagerFactory;

  @Before
  public void setUp() {
    this.serializationManagerFactory = new SerializationManagerFactory();
  }

  @Test
  public void whenCreate_thenReturnSerializationManager() {
    SerializationManager serializationManager = serializationManagerFactory.create();

    assertThat(serializationManager, is(not(nullValue())));
    assertThat(serializationManager, instanceOf(SerializationManager.class));
  }

}
