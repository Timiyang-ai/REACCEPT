@Override
  public boolean add(T e)
  {
    if (head - tail <= buffermask) {
      buffer[(int)(head & buffermask)] = e;
      head++;
      return true;
    }

    throw new BufferOverflowException();
  }