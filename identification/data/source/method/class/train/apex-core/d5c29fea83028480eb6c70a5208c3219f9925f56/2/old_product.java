public static AbstractReservoir newReservoir(final String id, final int capacity)
  {
    return new CircularBufferReservoir(id, capacity);
  }