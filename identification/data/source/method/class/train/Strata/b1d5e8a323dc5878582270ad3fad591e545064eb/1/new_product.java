@Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Tenor other = (Tenor) obj;
    return period.equals(other.period);
  }