public double reduce(String numberColumnName, NumericAggregateFunction function) {
    NumberColumn<?> column = table.numberColumn(numberColumnName);
    return function.summarize(column.where(selection));
  }