@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
      return JodaBeanUtils.equal(first, other.first) &&
          JodaBeanUtils.equal(second, other.second) &&
          JodaBeanUtils.equal(third, other.third);
    }
    return false;
  }