@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ObjDoublePair<?> other = (ObjDoublePair<?>) obj;
      return JodaBeanUtils.equal(getFirst(), other.getFirst()) &&
          JodaBeanUtils.equal(getSecond(), other.getSecond());
    }
    return false;
  }