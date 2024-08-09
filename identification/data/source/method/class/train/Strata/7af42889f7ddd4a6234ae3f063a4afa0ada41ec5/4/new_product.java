@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      PeriodAdjustment other = (PeriodAdjustment) obj;
      return JodaBeanUtils.equal(getPeriod(), other.getPeriod()) &&
          JodaBeanUtils.equal(getAdditionConvention(), other.getAdditionConvention()) &&
          JodaBeanUtils.equal(getAdjustment(), other.getAdjustment());
    }
    return false;
  }