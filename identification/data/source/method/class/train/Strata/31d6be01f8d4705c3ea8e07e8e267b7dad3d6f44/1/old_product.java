@Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StandardId that = (StandardId) o;
    return scheme.equals(that.scheme) && value.equals(that.value);

  }