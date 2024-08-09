public double reduce(String numberColumnName, AggregateFunction function) {
        Column column = table.column(numberColumnName);
        return function.summarize(column.where(selection));
    }