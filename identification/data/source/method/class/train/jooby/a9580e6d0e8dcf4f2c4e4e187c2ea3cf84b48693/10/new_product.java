public boolean matches(final MediaType that) {
    if (this == that || this.wildcardType || that.wildcardType) {
      // same or */*
      return true;
    }
    if (type.equals(that.type)) {
      if (subtype.equals(that.subtype) || this.wildcardSubtype) {
        return true;
      }
      if (subtype.startsWith("*")) {
        return subtype.substring(1).endsWith(that.subtype);
      }
    }
    return false;
  }