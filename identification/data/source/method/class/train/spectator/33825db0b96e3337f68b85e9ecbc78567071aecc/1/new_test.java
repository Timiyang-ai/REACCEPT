  private String get(Id id, String key) {
    for (Tag t : id.tags()) {
      if (key.equals(t.key())) {
        return t.value();
      }
    }
    return null;
  }