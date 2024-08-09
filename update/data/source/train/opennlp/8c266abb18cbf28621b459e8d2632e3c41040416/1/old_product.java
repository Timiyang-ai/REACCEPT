public boolean equals(Object o) {

    boolean result;

    if (o == this) {
      result = true;
    }
    else if (o instanceof Span) {
      Span s = (Span) o;

      result = (getStart() == s.getStart()) && 
          (getEnd() == s.getEnd()) &&
          (getType() != null ? type.equals(s.getType()) : true);
    }
    else {
      result = false;
    }

    return result;
  }