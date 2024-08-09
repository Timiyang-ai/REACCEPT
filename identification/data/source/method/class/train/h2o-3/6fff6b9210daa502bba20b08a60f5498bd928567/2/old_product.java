public double[] convert(RowData data, double[] rawData) throws PredictException {  
    for (String dataColumnName : data.keySet()) {
      Integer index = _modelColumnNameToIndexMap.get(dataColumnName);

      // Skip column names that are not known.
      // Skip the "response" column which should not be included in `rawData`
      if (index == null || index >= rawData.length) {
        continue;
      }

      Object o = data.get(dataColumnName);
      if (convertValue(dataColumnName, o, _domainMap.get(index), index, rawData)) {
        return rawData;
      }
    }
    return rawData;
  }