  private void memoizeTest(CountingSupplier countingSupplier) {
    Supplier<Integer> memoizedSupplier = Suppliers.memoize(countingSupplier);
    checkMemoize(countingSupplier, memoizedSupplier);
  }