public boolean isCrossCurrency() {
    // optimized for performance
    Currency firstCurrency = legs.get(0).getCurrency();
    for (int i = 1; i < legs.size(); i++) {
      if (!legs.get(i).getCurrency().equals(firstCurrency)) {
        return true;
      }
    }
    return false;
  }