public void add(T toAdd)
  {
    if (count == bufferlen) {
      throw overflow;
    }

    buffer[head++ % bufferlen] = toAdd;
    count++;
  }