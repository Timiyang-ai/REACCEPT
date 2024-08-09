public void load(Repository db, ObjectId id) throws IOException,
      ConfigInvalidException {
    try (RevWalk walk = new RevWalk(db)) {
      load(walk, id);
    }
  }