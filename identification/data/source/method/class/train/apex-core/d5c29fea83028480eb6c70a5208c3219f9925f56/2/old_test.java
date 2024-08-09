  private static AbstractReservoir newReservoir(final String reservoirClassName, final int capacity)
  {
    if (reservoirClassName == null) {
      System.clearProperty(AbstractReservoir.reservoirClassNameProperty);
    } else {
      System.setProperty(AbstractReservoir.reservoirClassNameProperty, reservoirClassName);
    }
    final String id = reservoirClassName == null ? "DefaultReservoir" : reservoirClassName;
    final AbstractReservoir reservoir = AbstractReservoir.newReservoir(id, capacity);
    return reservoir;
  }