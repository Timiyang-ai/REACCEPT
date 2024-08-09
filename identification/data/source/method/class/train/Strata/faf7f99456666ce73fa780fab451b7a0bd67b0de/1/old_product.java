@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Frequency) {
      Frequency other = (Frequency) obj;
      return period.equals(other.period);
    }
    return false;
  }