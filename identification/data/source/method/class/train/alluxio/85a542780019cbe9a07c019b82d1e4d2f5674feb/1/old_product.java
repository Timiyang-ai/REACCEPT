public boolean inodeIdExists(long id) {
    return singleindex.getFirst(id) != null;
  }