public T get()
  {
    if (head > tail) {
      return buffer[tail++ & buffermask];
    }

    throw underflow;
  }