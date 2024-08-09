@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ValueAdjustment other = (ValueAdjustment) obj;
      return JodaBeanUtils.equal(getModifyingValue(), other.getModifyingValue()) &&
          JodaBeanUtils.equal(getType(), other.getType());
    }
    return false;
  }