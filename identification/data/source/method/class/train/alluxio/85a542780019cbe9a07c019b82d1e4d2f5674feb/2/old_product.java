public boolean inodeIdExists(long id) {
    return mInodes.getFirstByField(mIdIndex, id) != null;
  }