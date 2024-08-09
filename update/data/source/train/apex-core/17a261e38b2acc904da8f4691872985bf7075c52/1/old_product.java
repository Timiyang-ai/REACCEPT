@Override
  public T get()
  {
    if (head > tail) {
      T t = buffer[(int)(tail & buffermask)];
      tail++;
      return t;
    }

    throw new BufferUnderflowException();
  }