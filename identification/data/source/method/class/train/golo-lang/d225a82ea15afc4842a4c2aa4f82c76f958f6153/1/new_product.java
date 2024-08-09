@Override
  public boolean containsAll(Collection<?> c) {
    for (Object elt : c) {
      if (!this.contains(elt)) { return false; }
    }
    return true;
  }