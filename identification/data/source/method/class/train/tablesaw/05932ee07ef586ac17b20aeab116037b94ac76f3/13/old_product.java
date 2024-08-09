@SuppressWarnings("unchecked")
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
                ColumnType type = function.returnType();
                AbstractColumn resultColumn = (AbstractColumn) Column.create(colName, type);
                for (TableSlice subTable : getSlices()) {
                    Object result = function.summarize(subTable.column(columnName));
                    if (functionCount == 0) {
                        groupColumn.append(subTable.name());
                    }
                    if (result instanceof Number) {
                        Number number = (Number) result;
                        resultColumn.append(number.doubleValue());
                    } else {
                        resultColumn.append(result);
                    }
                }
                groupTable.addColumns(resultColumn);
                functionCount++;
            }
        }
        return splitGroupingColumn(groupTable);
    }