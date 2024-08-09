@Override
  public T remove()
  {
    if (head > tail) {
      T t = buffer[(int)(tail & buffermask)];
      tail++;
      return t;
    }

    throw new BufferUnderflowException();
  }