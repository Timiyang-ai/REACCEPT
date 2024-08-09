private Iterable<String> buildQueries(final String expression) {
    // Why an explicit iterable rather than a list? If there is
    // a syntax error in the expression, the calling code discovers it
    // before we try to parse it to do substitutions on the parse tree.
    return new Iterable<String>() {
      public Iterator<String> iterator() {
        return new Iterator<String>() {
          int i = 0;

          public void remove() {
            throw new UnsupportedOperationException();
          }

          public String next() {
            switch (i++) {
            case 0:
              return buildQuery(expression);
            case 1:
              return buildQuery2(expression);
            default:
              throw new NoSuchElementException();
            }
          }

          public boolean hasNext() {
            return i < 2;
          }
        };
      }
    };
  }