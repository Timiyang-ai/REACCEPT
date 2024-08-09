static <E> PeekingIterator<E> comparing(PeekingIterator<E> first,
          PeekingIterator<E> second, Comparator<E> comparator) {
      return new PeekingIterator<E>() {
        @Override public boolean hasNext() {
          return first.hasNext() || second.hasNext();
        }
        @Override public E next() {
          if (!first.hasNext()) {
            return second.next();
          } else if (!second.hasNext()) {
            return first.next();
          }
          boolean greaterOrEqual = (comparator.compare(first.peek(), second.peek()) >= 0);
          return greaterOrEqual ? first.next() : second.next();
        }
        @Override public E peek() {
          if (!first.hasNext()) {
            return second.peek();
          } else if (!second.hasNext()) {
            return first.peek();
          }
          boolean greaterOrEqual = (comparator.compare(first.peek(), second.peek()) >= 0);
          return greaterOrEqual ? first.peek() : second.peek();
        }
      };
    }