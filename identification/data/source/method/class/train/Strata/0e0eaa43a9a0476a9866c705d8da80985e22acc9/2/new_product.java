public static boolean isSemiAnnualRollDate(LocalDate date) {
    if (date.getDayOfMonth() != IMM_DAY) {
      return false;
    }
    int month = date.getMonthValue();
    return month == INDEX_ROLL_MONTHS.get(0) || month == INDEX_ROLL_MONTHS.get(1);
  }