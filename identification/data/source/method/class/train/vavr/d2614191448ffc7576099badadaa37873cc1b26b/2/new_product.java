<T> Stream<T> create(Iterator<? extends T> iterator) {
                    if (iterator.hasNext()) {
                        // we need to get the head, otherwise a tail call would get the head instead
                        final T head = iterator.next();
                        return new Cons<>(() -> head, () -> create(iterator));
                    } else {
                        return Nil.instance();
                    }
                }