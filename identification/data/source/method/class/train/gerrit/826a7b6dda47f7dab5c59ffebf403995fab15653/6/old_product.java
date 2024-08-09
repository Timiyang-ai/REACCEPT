public void load(Repository db) throws IOException, ConfigInvalidException {
    Ref ref = db.getRef(getRefName());
    load(db, ref != null ? ref.getObjectId() : null);
  }