@CheckReturnValue
  public Joiner useForNull(final String nullText) {
    checkNotNull(nullText);
    return new Joiner(this) {
      @Override CharSequence toString(Object part) {
        return (part == null) ? nullText : Joiner.this.toString(part);
      }

      @Override public Joiner useForNull(String nullText) {
        checkNotNull(nullText); // weird: just to satisfy NullPointerTester.
        throw new UnsupportedOperationException("already specified useForNull");
      }

      @Override public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
      }
    };
  }