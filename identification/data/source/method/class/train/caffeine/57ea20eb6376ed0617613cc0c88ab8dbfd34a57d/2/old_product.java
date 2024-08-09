static <E> PeekingIterator<E> concat(PeekingIterator<E> first, PeekingIterator<E> second) {
      return new PeekingIterator<E>() {
        @Override public boolean hasNext() {
          return first.hasNext() || second.hasNext();
        }
        @Override public E next() {
          if (first.hasNext()) {
            return first.next();
          } else if (second.hasNext()) {
            return second.next();
          }
          throw new NoSuchElementException();
        }
        @Override public E peek() {
          if (first.hasNext()) {
            return first.peek();
          } else if (second.hasNext()) {
            return second.peek();
          }
          return null;
        }
      };
    }