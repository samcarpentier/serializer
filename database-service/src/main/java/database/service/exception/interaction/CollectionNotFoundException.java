package database.service.exception.interaction;

public class CollectionNotFoundException extends DatabaseInteractionException {

  private static final long serialVersionUID = 3096960719469195640L;

  public CollectionNotFoundException(String message) {
    super(message);
  }

}
