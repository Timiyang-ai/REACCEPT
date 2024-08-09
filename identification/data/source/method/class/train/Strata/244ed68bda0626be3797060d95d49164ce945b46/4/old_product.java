@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
      return JodaBeanUtils.equal(getFirst(), other.getFirst()) &&
          JodaBeanUtils.equal(getSecond(), other.getSecond()) &&
          JodaBeanUtils.equal(getThird(), other.getThird());
    }
    return false;
  }