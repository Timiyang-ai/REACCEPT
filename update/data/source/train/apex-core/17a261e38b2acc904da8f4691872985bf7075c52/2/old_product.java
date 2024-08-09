@Override
  public void add(T toAdd)
  {
    if (head - tail <= buffermask) {
      buffer[(int)(head & buffermask)] = toAdd;
      head++;
      return;
    }

    throw new BufferOverflowException();
  }