public void setColumns(String... columnIds) {
        // Must extract to an explicitly typed variable because otherwise javac
        // cannot determine which overload of setColumnOrder to use
        Column<T, ?>[] newColumnOrder = Stream.of(columnIds)
                .map((Function<String, Column<T, ?>>) id -> {
                    Column<T, ?> column = getColumn(id);
                    if (column == null) {
                        column = addColumn(id);
                    }
                    return column;
                }).toArray(Column[]::new);
        setColumnOrder(newColumnOrder);

        // The columns to remove are now at the end of the column list
        getColumns().stream().skip(columnIds.length)
                .forEach(this::removeColumn);
    }