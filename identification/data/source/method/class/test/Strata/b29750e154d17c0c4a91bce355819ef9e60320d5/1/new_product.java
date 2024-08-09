public LocalDate calculate(Supplier<LocalDate> endDateSupplier, Supplier<LocalDate> lastFixingDateSupplier) {
    switch (type) {
      case FIXED:
        return date;
      case END:
        return endDateSupplier.get();
      case LAST_FIXING:
        return lastFixingDateSupplier.get();
    }
    throw new IllegalStateException("Unknown curve node type");
  }