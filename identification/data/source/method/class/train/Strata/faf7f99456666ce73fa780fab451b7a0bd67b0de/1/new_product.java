@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Frequency other = (Frequency) obj;
    return period.equals(other.period);
  }