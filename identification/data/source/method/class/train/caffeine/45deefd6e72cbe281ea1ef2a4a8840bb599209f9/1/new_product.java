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
          E o1 = first.peek();
          E o2 = second.peek();
          boolean greaterOrEqual = (comparator.compare(o1, o2) >= 0);
          return greaterOrEqual ? first.next() : second.next();
        }
        @Override public @Nullable E peek() {
          if (!first.hasNext()) {
            return second.peek();
          } else if (!second.hasNext()) {
            return first.peek();
          }
          E o1 = first.peek();
          E o2 = second.peek();
          boolean greaterOrEqual = (comparator.compare(o1, o2) >= 0);
          return greaterOrEqual ? first.peek() : second.peek();
        }
      };
    }