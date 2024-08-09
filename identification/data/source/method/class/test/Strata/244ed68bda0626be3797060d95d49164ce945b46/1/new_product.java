@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return JodaBeanUtils.equal(first, other.first) &&
          JodaBeanUtils.equal(second, other.second);
    }
    return false;
  }