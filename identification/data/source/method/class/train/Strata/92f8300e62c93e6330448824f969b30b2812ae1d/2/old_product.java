public CsvRowOutputWithHeaders writeCell(String header, double value) {
      return writeCell(header, Double.valueOf(value));
    }