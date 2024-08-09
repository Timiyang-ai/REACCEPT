public LocalDate calculate(Supplier<LocalDate> lastPaymentDateSupplier, Supplier<LocalDate> lastFixingDateSupplier) {
    switch (type) {
      case FIXED:
        return date;
      case END:
        return lastPaymentDateSupplier.get();
      case LAST_FIXING:
        return lastFixingDateSupplier.get();
    }
    throw new IllegalStateException("Unknown curve node type");
  }