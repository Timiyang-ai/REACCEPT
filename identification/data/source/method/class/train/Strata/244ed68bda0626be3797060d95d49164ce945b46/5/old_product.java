@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ObjIntPair<?> other = (ObjIntPair<?>) obj;
      return JodaBeanUtils.equal(getFirst(), other.getFirst()) &&
          (getSecond() == other.getSecond());
    }
    return false;
  }