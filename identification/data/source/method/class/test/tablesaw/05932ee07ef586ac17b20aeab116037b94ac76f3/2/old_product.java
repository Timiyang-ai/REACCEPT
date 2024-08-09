public Table aggregate(ArrayListMultimap<String, AggregateFunction> functions) {
        Preconditions.checkArgument(!getSubTables().isEmpty());
        Table groupTable = Table.create(getSourceTable().name() + " summary");
        StringColumn groupColumn = StringColumn.create("Group", size());
        groupTable.addColumn(groupColumn);
        for (Map.Entry<String, Collection<AggregateFunction>> entry : functions.asMap().entrySet()) {
            String columnName = entry.getKey();
            int functionCount = 0;
            for (AggregateFunction function : entry.getValue()) {
                String colName = aggregateColumnName(columnName, function.functionName());
                NumberColumn resultColumn = NumberColumn.create(colName, getSubTables().size());
                for (TableSlice subTable : getSubTables()) {
                    double result = subTable.reduce(columnName, function);
                    if (functionCount == 0) {
                        groupColumn.append(subTable.name());
                    }
                    resultColumn.append(result);
                }
                groupTable.addColumn(resultColumn);
                functionCount++;
            }
        }
        Table result = splitGroupingColumn(groupTable);
        for (CategoricalColumn column : columnsToRemove) {
            result.removeColumns(column);
        }
        return result;
    }