static LocalDate getNextSemiAnnualRollDate(LocalDate date) {

    int day = date.getDayOfMonth();
    int month = date.getMonthValue();
    int year = date.getYear();
    if (isSemiAnnualRollDate(date)) { //on an index roll 
      if (month == INDEX_ROLL_MONTHS.get(0)) {
        return LocalDate.of(year, INDEX_ROLL_MONTHS.get(1), IMM_DAY);
      } else {
        return LocalDate.of(year + 1, INDEX_ROLL_MONTHS.get(0), IMM_DAY);
      }
    } else {
      if (month < INDEX_ROLL_MONTHS.get(0)) {
        return LocalDate.of(year, INDEX_ROLL_MONTHS.get(0), IMM_DAY);
      } else if (month == INDEX_ROLL_MONTHS.get(0)) {
        if (day < IMM_DAY) {
          return LocalDate.of(year, month, IMM_DAY);
        } else {
          return LocalDate.of(year, INDEX_ROLL_MONTHS.get(1), IMM_DAY);
        }
      } else if (month < INDEX_ROLL_MONTHS.get(1)) {
        return LocalDate.of(year, INDEX_ROLL_MONTHS.get(1), IMM_DAY);
      } else if (month == INDEX_ROLL_MONTHS.get(1)) {
        if (day < IMM_DAY) {
          return LocalDate.of(year, month, IMM_DAY);
        } else {
          return LocalDate.of(year + 1, INDEX_ROLL_MONTHS.get(0), IMM_DAY);
        }
      } else {
        return LocalDate.of(year + 1, INDEX_ROLL_MONTHS.get(0), IMM_DAY);
      }
    }
  }