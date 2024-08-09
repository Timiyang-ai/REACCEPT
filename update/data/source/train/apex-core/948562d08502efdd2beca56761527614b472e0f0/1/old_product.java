public T get()
  {
    if (count > 0) {
      count--;
      return buffer[tail++ % bufferlen];
    }

    throw underflow;
  }