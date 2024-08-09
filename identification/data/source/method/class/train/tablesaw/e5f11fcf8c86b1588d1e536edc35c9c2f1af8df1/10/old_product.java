public double reduce(String numberColumnName, AggregateFunction function) {
        NumberColumn column = (NumberColumn) column(numberColumnName);
        return function.summarize(column.where(selection));
    }