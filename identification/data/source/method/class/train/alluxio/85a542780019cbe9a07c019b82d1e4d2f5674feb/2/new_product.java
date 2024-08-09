public boolean inodeIdExists(long id) {
    return mInodes.getFirstByField(ID_INDEX, id) != null;
  }