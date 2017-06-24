package database.model;

import java.io.Serializable;
import java.util.*;

import com.google.common.collect.Maps;

import database.exception.interaction.*;

public class Database implements Serializable {

  private static final long serialVersionUID = 5712719555769453715L;

  private String name;
  private Map<String, DatabaseCollection> collections = Maps.newHashMap();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<String, DatabaseCollection> getCollections() {
    return collections;
  }

  public DatabaseCollection getCollection(String collectionName) throws CollectionNotFoundException {
    return Optional.ofNullable(collections.get(collectionName)).orElseThrow(() -> new CollectionNotFoundException(""));
  }

  public void setCollections(Map<String, DatabaseCollection> collections) {
    this.collections = collections;
  }

  public void addCollection(String collectionName) throws DuplicateCollectionException {
    if (collections.containsKey(collectionName)) {
      throw new DuplicateCollectionException(
          String.format("Cannot create new collection with name [%s]. Collection already existing.", collectionName));
    }

    collections.put(collectionName, new DatabaseCollection(collectionName));
  }

}
