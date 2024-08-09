public Table aggregate(ListMultimap<String, AggregateFunction> functions) {
        Preconditions.checkArgument(!getSlices().isEmpty());
        Table groupTable = summaryTableName(sourceTable);
        StringColumn groupColumn = StringColumn.create("Group", size());
        groupTable.addColumns(groupColumn);
        for (Map.Entry<String, Collection<AggregateFunction>> entry : functions.asMap().entrySet()) {
            String columnName = entry.getKey();
            int functionCount = 0;
            for (AggregateFunction function : entry.getValue()) {
                String colName = aggregateColumnName(columnName, function.functionName());
                NumberColumn resultColumn = DoubleColumn.create(colName, getSlices().size());
                for (TableSlice subTable : getSlices()) {
                    double result = subTable.reduce(columnName, function);
                    if (functionCount == 0) {
                        groupColumn.append(subTable.name());
                    }
                    resultColumn.append(result);
                }
                groupTable.addColumns(resultColumn);
                functionCount++;
            }
        }
        return splitGroupingColumn(groupTable);
    }