public double reduce(String numberColumnName, NumericAggregateFunction function) {
        NumberColumn column = table.numberColumn(numberColumnName);
        return (Double) function.summarize(column.where(selection));
    }