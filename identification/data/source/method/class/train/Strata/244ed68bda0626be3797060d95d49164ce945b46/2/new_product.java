@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AdjustableDate other = (AdjustableDate) obj;
      return JodaBeanUtils.equal(unadjusted, other.unadjusted) &&
          JodaBeanUtils.equal(adjustment, other.adjustment);
    }
    return false;
  }