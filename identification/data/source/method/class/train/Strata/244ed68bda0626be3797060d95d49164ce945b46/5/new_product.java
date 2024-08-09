@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ObjIntPair<?> other = (ObjIntPair<?>) obj;
      return JodaBeanUtils.equal(first, other.first) &&
          (second == other.second);
    }
    return false;
  }