public static String datePeriod(LocalDate start, LocalDate end) {
    int months = Math.toIntExact(MONTHS.between(start, end.plusDays(3)));
    if (months > 0) {
      return Tenor.of(Period.ofMonths((int) months)).normalized().toString();
    } else {
      return Tenor.of(Period.ofDays((int) start.until(end, ChronoUnit.DAYS))).toString();
    }
  }