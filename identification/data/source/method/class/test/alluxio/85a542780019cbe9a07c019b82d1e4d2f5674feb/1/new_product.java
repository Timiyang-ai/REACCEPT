public boolean inodeIdExists(long id) {
    return mInodeStore.get(id).isPresent();
  }