@Override
    public void drain(Consumer<E> consumer) {
      long head = readCounter.get();
      long tail = writeCounter.get();
      long size = (tail - head);
      if (size == 0) {
        return;
      }
      do {
        int index = (int) (head & BUFFER_MASK);
        AtomicReference<E> slot = buffer[index];
        E e = slot.get();
        if (e == null) {
          // not published yet
          break;
        }
        slot.lazySet(null);
        consumer.accept(e);
        head++;
      } while (head != tail);
      readCounter.lazySet(head);
    }