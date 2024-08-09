public double reduce(String numberColumnName, AggregateFunction function) {
        Column column = column(numberColumnName);
        return function.summarize(column.where(selection));
    }