public double reduce(String numberColumnName, NumericAggregateFunction function) {
    NumericColumn<?> column = table.numberColumn(numberColumnName);
    if (hasSelection()) {
      return function.summarize(column.where(selection));
    }
    return function.summarize(column);
  }