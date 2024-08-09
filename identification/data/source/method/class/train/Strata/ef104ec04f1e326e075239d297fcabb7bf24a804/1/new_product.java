private void parse(
      CharSource charSource,
      ListMultimap<String, CurveSensitivities> parsed,
      List<FailureItem> failures) {

    try (CsvIterator csv = CsvIterator.of(charSource, true)) {
      if (!csv.containsHeader(TENOR_HEADER) && !csv.containsHeader(DATE_HEADER)) {
        failures.add(FailureItem.of(
            FailureReason.PARSING, "CSV file could not be parsed as sensitivities, invalid format"));
      } else if (csv.containsHeader(REFERENCE_HEADER) &&
          csv.containsHeader(TYPE_HEADER) &&
          csv.containsHeader(VALUE_HEADER)) {
        parseStandardFormat(csv, parsed, failures);
      } else if (csv.containsHeader(REFERENCE_HEADER)) {
        parseListFormat(csv, parsed, failures);
      } else {
        parseGridFormat(csv, parsed, failures);
      }
    } catch (RuntimeException ex) {
      failures.add(FailureItem.of(FailureReason.PARSING, ex, "CSV file could not be parsed: {}", ex.getMessage()));
    }
  }