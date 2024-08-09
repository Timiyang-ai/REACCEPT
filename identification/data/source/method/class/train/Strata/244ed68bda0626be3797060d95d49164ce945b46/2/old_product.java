@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AdjustableDate other = (AdjustableDate) obj;
      return JodaBeanUtils.equal(getUnadjusted(), other.getUnadjusted()) &&
          JodaBeanUtils.equal(getAdjustment(), other.getAdjustment());
    }
    return false;
  }