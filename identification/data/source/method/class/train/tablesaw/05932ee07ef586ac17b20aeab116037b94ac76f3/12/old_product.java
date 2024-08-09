public Table aggregate(String colName1, AggregateFunction... func1) {
        ArrayListMultimap<String, AggregateFunction> map = ArrayListMultimap.create();
        map.putAll(colName1, Lists.newArrayList(func1));
        Table result = aggregate(map);
        for (CategoricalColumn column : columnsToRemove) {
            result.removeColumns(column);
        }
        return result;

    }