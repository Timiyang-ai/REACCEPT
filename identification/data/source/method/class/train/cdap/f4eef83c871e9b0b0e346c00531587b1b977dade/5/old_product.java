public <T extends Comparable<T>> Builder addRangeCondition(String field,
                                                               @Nullable T lower,
                                                               @Nullable T upper) {
      Preconditions.checkArgument(field != null && !field.isEmpty(), "field name cannot be null or empty.");
      Preconditions.checkArgument(lower == null || !lower.equals(upper), "Unsatisfiable condition: " +
        "lower bound and upper bound have equal value: '" + lower + "'");
      if (map.containsKey(field)) {
        throw new IllegalArgumentException(String.format("Field '%s' already exists in partition filter.", field));
      }
      if (null == lower && null == upper) { // filter is pointless if there is no bound
        return this;
      }
      map.put(field, new Condition<T>(field, lower, upper));
      return this;
    }