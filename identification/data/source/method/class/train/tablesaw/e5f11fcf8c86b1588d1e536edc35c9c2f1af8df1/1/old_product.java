public double reduce(String numberColumnName, AggregateFunction function) {
        NumberColumn column = (NumberColumn) column(numberColumnName);
        return function.agg(column.select(selection));
    }