public <T extends Comparable<T>> Builder addValueCondition(String field, T value) {
      Preconditions.checkArgument(field != null && !field.isEmpty(), "field name cannot be null or empty.");
      Preconditions.checkArgument(value != null, "condition value cannot be null.");
      if (map.containsKey(field)) {
        throw new IllegalArgumentException(String.format("Field '%s' already exists in partition filter.", field));
      }
      map.put(field, new Condition<>(field, value));
      return this;
    }