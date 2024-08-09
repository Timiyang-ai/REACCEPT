@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FxRate other = (FxRate) obj;
      return JodaBeanUtils.equal(getPair(), other.getPair()) &&
          JodaBeanUtils.equal(getRate(), other.getRate());
    }
    return false;
  }