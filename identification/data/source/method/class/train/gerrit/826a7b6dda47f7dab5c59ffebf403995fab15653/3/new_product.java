public void load(Repository db, ObjectId id) throws IOException,
      ConfigInvalidException {
    reader = db.newObjectReader();
    try {
      revision = id != null ? new RevWalk(reader).parseCommit(id) : null;
      onLoad();
    } finally {
      reader.release();
      reader = null;
    }
  }