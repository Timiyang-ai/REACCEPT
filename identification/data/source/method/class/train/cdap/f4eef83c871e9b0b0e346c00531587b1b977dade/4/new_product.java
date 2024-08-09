public <T extends Comparable<T>> Builder addValueCondition(String field, T value) {
      if (field == null || field.isEmpty()) {
        throw new IllegalArgumentException("field name cannot be null or empty.");
      }
      if (value == null) {
        throw new IllegalArgumentException("condition value cannot be null.");
      }
      if (map.containsKey(field)) {
        throw new IllegalArgumentException(String.format("Field '%s' already exists in partition filter.", field));
      }
      map.put(field, new Condition<>(field, value));
      return this;
    }