public boolean isConventional() {
    // If the pair is in the configuration file it is a market convention pair
    if (CONFIGURED.containsKey(this)) {
      return true;
    }
    // Get the priorities of the currencies to determine which should be the base
    Integer basePriority = CURRENCY_ORDERING.getOrDefault(base, Integer.MAX_VALUE);
    Integer counterPriority = CURRENCY_ORDERING.getOrDefault(counter, Integer.MAX_VALUE);

    // If a currency is earlier in the list it has a higher priority
    if (basePriority < counterPriority) {
      return true;
    } else if (basePriority > counterPriority) {
      return false;
    }
    // Neither currency is included in the list defining the ordering.
    // Use lexicographical ordering. It's arbitrary but consistent. This ensures two CurrencyPair instances
    // created independently for the same two currencies will always choose the same conventional pair.
    // The natural ordering of Currency is the same as the natural ordering of the currency code but
    // comparing the Currency instances is more efficient.
    return base.compareTo(counter) < 0;
  }