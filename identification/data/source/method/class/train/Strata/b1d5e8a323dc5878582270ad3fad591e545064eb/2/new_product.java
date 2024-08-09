@Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Tenor)) {
      return false;
    }
    return period.equals(((Tenor) obj).period);
  }