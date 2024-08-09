public static AbstractReservoir newReservoir(final String id, final int capacity)
  {
    String reservoirClassName = System.getProperty(reservoirClassNameProperty);
    if (reservoirClassName == null) {
      if (capacity >=  USE_SPSC_CAPACITY) {
        return new SpscArrayQueueReservoir(id, capacity);
      } else {
        return new ArrayBlockingQueueReservoir(id, capacity);
      }
    } else if (reservoirClassName.equals(SpscArrayQueueReservoir.class.getName())) {
      return new SpscArrayQueueReservoir(id, capacity);
    } else if (reservoirClassName.equals(CircularBufferReservoir.class.getName())) {
      return new CircularBufferReservoir(id, capacity);
    } else if (reservoirClassName.equals(ArrayBlockingQueueReservoir.class.getName())) {
      return new ArrayBlockingQueueReservoir(id, capacity);
    } else {
      try {
        final Constructor<?> constructor = Class.forName(reservoirClassName).getConstructor(String.class, int.class);
        return (AbstractReservoir)constructor.newInstance(id, capacity);
      } catch (ReflectiveOperationException e) {
        logger.debug("Fail to construct reservoir {}", reservoirClassName, e);
        throw new RuntimeException("Fail to construct reservoir " + reservoirClassName, e);
      }
    }
  }