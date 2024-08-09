public static <E> int drain(BlockingQueue<E> q, Collection<? super E> buffer, int maxElements,
      long timeout, TimeUnit unit) throws InterruptedException {
    Preconditions.checkNotNull(buffer);

    long remainingNanos = unit.toNanos(timeout);
    long end = System.nanoTime() + unit.toNanos(timeout);

    int added = 0;
    while (added < maxElements) {
      // we could rely solely on #poll, but #drainTo might be more efficient when there are multiple
      // elements already available (e.g. LinkedBlockingQueue#drainTo locks only once)
      added += q.drainTo(buffer, maxElements - added);
      if (added < maxElements) { // not enough elements immediately available; will have to poll
        E e = q.poll(remainingNanos, TimeUnit.NANOSECONDS);
        if (e == null) {
          break; // we already waited enough, and there are no more elements in sight
        }
        buffer.add(e);
        added++;
        if (added >= maxElements) {
          break; // simply avoiding an extra nanoTime() invocation
        }
        remainingNanos = end - System.nanoTime();
      }
    }
    return added;
  }