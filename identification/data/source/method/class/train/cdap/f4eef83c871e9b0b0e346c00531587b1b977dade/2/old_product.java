public <V extends Comparable> boolean match(V value) {
      try {
        // if lower and upper are identical, then this represents an equality condition.
        if (isSingleValue()) {
          return getValue().equals(value);
        }
        @SuppressWarnings("unchecked")
        boolean matches =
          (lower == null || lower.compareTo((T) value) <= 0) &&
            (upper == null || upper.compareTo((T) value) > 0);
        return matches;

      } catch (ClassCastException e) {
        // this should never happen because we make sure that partition keys and filters
        // match the field types declared for the partitioning. But just to be sure:
        throw new IllegalArgumentException("Incompatible partition filter: condition for field '" + fieldName +
                                             "' is on " + determineClass() + " but partition key value '" + value
                                             + "' is of " + value.getClass());
      }
    }