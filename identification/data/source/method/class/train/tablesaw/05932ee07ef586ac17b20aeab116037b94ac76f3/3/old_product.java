public Table aggregate(String colName1, AggregateFunction... functions) {
        ArrayListMultimap<String, AggregateFunction> columnFunctionMap = ArrayListMultimap.create();
        columnFunctionMap.putAll(colName1, Lists.newArrayList(functions));
/*
        Table result = aggregate(columnFunctionMap);
        for (CategoricalColumn column : columnsToRemove) {
            result.removeColumns(column);
        }
        return result;
*/

        return null;
    }