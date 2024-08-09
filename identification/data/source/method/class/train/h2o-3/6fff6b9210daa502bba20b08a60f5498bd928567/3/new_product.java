public double[] convert(RowData data, double[] rawData) throws PredictException {  
    for (String dataColumnName : data.keySet()) {
      Integer index = _modelColumnNameToIndexMap.get(dataColumnName);

      // Skip column names that are not known.
      // Skip the "response" column which should not be included in `rawData`
      if (index == null || index >= rawData.length) {
        continue;
      }

      String[] domainValues = _m.getDomainValues(index);
      Object o = data.get(dataColumnName);

      if (convertValue(dataColumnName, o, domainValues, index, rawData)) {
        return rawData;
      }
    }
    return rawData;
  }