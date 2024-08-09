public void load(Repository db, ObjectId id) throws IOException,
      ConfigInvalidException {
    if (id != null) {
      reader = db.newObjectReader();
      try {
        revision = new RevWalk(reader).parseCommit(id);
        onLoad();
      } finally {
        reader.release();
        reader = null;
      }
    } else {
      // The branch does not yet exist.
      revision = null;
      onLoad();
    }
  }