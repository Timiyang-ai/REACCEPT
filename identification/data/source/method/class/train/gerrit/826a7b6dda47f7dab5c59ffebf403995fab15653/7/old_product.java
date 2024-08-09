public void load(RevWalk walk, ObjectId id) throws IOException, ConfigInvalidException {
    this.reader = walk.getObjectReader();
    try {
      revision = id != null ? new RevWalk(reader).parseCommit(id) : null;
      onLoad();
    } finally {
      reader = null;
    }
  }