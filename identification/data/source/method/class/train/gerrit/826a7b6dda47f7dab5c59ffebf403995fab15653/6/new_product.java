public void load(Repository db) throws IOException, ConfigInvalidException {
    Ref ref = db.getRefDatabase().exactRef(getRefName());
    load(db, ref != null ? ref.getObjectId() : null);
  }