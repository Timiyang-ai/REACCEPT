public static <T> Stream<ObjIntPair<T>> zipWithIndex(Stream<T> stream) {
    Spliterator<T> split1 = stream.spliterator();
    Iterator<T> it1 = Spliterators.iterator(split1);
    Iterator<ObjIntPair<T>> it = new Iterator<ObjIntPair<T>>() {
      int index = 0;

      @Override
      public boolean hasNext() {
        return it1.hasNext();
      }

      @Override
      public ObjIntPair<T> next() {
        return ObjIntPair.of(it1.next(), index++);
      }
    };
    Spliterator<ObjIntPair<T>> split = Spliterators.spliterator(it, split1.getExactSizeIfKnown(), split1.characteristics());
    return StreamSupport.stream(split, false);
  }