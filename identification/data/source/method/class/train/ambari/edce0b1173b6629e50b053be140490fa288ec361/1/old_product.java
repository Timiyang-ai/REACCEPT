public static Predicate fromMap(Map<String, Object> map) {
    Predicate predicate = null;

    if ((map != null) && !map.isEmpty()) {
      if (map.size() == 1) {
        Map.Entry<String, Object> entry = map.entrySet().iterator().next();
        String name = entry.getKey();

        Class<? extends Predicate> predicateClass = PredicateClassFactory.getPredicateClass(name);

        if (predicateClass == null) {
          throw new IllegalArgumentException(String.format("Unexpected predicate name - %s", name));
        } else {
          try {
            // Dynamically locate and invoke the static toMap method for the named Predicate
            // implementation using reflection
            Method method = predicateClass.getMethod("fromMap", Map.class);
            if (method == null) {
              throw new UnsupportedOperationException(String.format("Cannot translate data to a %s - %s", predicateClass.getName(), "Failed to find toMap method"));
            } else {
              predicate = (Predicate) method.invoke(null, Collections.singletonMap(name, entry.getValue()));
            }
          } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new UnsupportedOperationException(String.format("Cannot translate data to a %s - %s", predicateClass.getName(), e.getLocalizedMessage()), e);
          }
        }
      } else {
        throw new IllegalArgumentException(String.format("Too many map entries have been encountered - %d", map.size()));
      }
    }

    return predicate;
  }