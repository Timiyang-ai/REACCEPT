public CsvRowOutputWithHeaders writeCell(String header, Object value) {
      return writeCell(header, JodaBeanUtils.stringConverter().convertToString(value));
    }