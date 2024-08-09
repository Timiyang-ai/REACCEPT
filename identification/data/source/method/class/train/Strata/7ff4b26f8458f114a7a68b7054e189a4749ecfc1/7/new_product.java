public default LocalDate shift(LocalDate date, int amount) {
    ArgChecker.notNull(date, "date");
    LocalDate adjusted = date;
    if (amount > 0) {
      for (int i = 0; i < amount; i++) {
        adjusted = next(adjusted);
      }
    } else if (amount < 0) {
      for (int i = 0; i > amount; i--) {
        adjusted = previous(adjusted);
      }
    }
    return adjusted;
  }