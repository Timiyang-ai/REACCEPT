@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FxRate other = (FxRate) obj;
      return JodaBeanUtils.equal(pair, other.pair) &&
          JodaBeanUtils.equal(rate, other.rate);
    }
    return false;
  }