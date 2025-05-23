@SuppressWarnings({ "unchecked", "rawtypes" })
    public Table aggregate(ListMultimap<String, AggregateFunction<?,?>> functions) {
        Preconditions.checkArgument(!getSlices().isEmpty());
        Table groupTable = summaryTableName(sourceTable);
        StringColumn groupColumn = StringColumn.create("Group");
        groupTable.addColumns(groupColumn);
        boolean firstFunction = true;
        for (Map.Entry<String, Collection<AggregateFunction<?,?>>> entry : functions.asMap().entrySet()) {
            String columnName = entry.getKey();
            for (AggregateFunction function : entry.getValue()) {
                String colName = aggregateColumnName(columnName, function.functionName());
                ColumnType type = function.returnType();
                Column resultColumn = type.create(colName);
                for (TableSlice subTable : getSlices()) {
                    Object result = function.summarize(subTable.column(columnName));
                    if (firstFunction) {
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
                firstFunction = false;
            }
        }
        return splitGroupingColumn(groupTable);
    }