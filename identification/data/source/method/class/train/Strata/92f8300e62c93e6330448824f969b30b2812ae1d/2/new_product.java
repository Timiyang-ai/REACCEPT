public CsvRowOutputWithHeaders writeCell(String header, double value) {
      String str = BigDecimal.valueOf(value).toPlainString();
      return writeCell(header, str.endsWith(".0") ? str.substring(0, str.length() - 2) : str);
    }