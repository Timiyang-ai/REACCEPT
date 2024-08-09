  private static <T> int drain(
      BlockingQueue<T> q,
      Collection<? super T> buffer,
      int maxElements,
      long timeout,
      TimeUnit unit,
      boolean interruptibly)
      throws InterruptedException {
    return interruptibly
        ? Queues.drain(q, buffer, maxElements, timeout, unit)
        : Queues.drainUninterruptibly(q, buffer, maxElements, timeout, unit);
  }