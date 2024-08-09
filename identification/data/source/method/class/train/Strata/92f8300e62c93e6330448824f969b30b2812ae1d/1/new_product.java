public CsvRowOutputWithHeaders writeCell(String header, Object value) {
      if (value instanceof Double) {
        return writeCell(header, ((Double) value).doubleValue());
      } else if (value instanceof Float) {
        return writeCell(header, ((Float) value).doubleValue());
      }
      return writeCell(header, JodaBeanUtils.stringConverter().convertToString(value));
    }