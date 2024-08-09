@Override
  public boolean equals(Object o) {
    if (o == null || !(o instanceof Tenor)) {
      return false;
    }
    return period.equals(((Tenor) o).period);
  }