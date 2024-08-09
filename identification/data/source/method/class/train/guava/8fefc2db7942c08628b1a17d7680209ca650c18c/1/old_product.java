@CheckReturnValue
  public Joiner skipNulls() {
    return new Joiner(this) {
      @Override public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts)
          throws IOException {
        checkNotNull(appendable, "appendable");
        checkNotNull(parts, "parts");
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(Joiner.this.toString(part));
            break;
          }
        }
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(separator);
            appendable.append(Joiner.this.toString(part));
          }
        }
        return appendable;
      }

      @Override public Joiner useForNull(String nullText) {
        checkNotNull(nullText); // weird: just to satisfy NullPointerTester.
        throw new UnsupportedOperationException("already specified skipNulls");
      }

      @Override public MapJoiner withKeyValueSeparator(String kvs) {
        checkNotNull(kvs); // weird: just to satisfy NullPointerTester.
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
      }
    };
  }