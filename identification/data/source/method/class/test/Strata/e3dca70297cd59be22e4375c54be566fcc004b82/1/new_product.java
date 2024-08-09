public boolean isCrossCurrency() {
    // optimized for performance
    int size = legs.size();
    if (size > 1) {
      Currency firstCurrency = legs.get(0).getCurrency();
      for (int i = 1; i < size; i++) {
        if (!legs.get(i).getCurrency().equals(firstCurrency)) {
          return true;
        }
      }
    }
    return false;
  }