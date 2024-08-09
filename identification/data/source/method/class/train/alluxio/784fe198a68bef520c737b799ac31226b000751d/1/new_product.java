default Optional<MutableInode<?>> getMutable(long id) {
    return getMutable(id, ReadOption.defaults());
  }