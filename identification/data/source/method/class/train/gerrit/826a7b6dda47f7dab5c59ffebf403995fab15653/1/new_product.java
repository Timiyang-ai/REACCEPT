public void load(RevWalk walk, ObjectId id) throws IOException, ConfigInvalidException {
    this.rw = walk;
    this.reader = walk.getObjectReader();
    try {
      revision = id != null ? walk.parseCommit(id) : null;
      onLoad();
    } finally {
      this.rw = null;
      this.reader = null;
    }
  }