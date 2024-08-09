public void add(T toAdd)
  {
    if (head - tail <= buffermask) {
      buffer[head++ & buffermask] = toAdd;
      return;
    }

    throw overflow;
  }