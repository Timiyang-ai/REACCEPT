public void setColumns(Column<T, ?>... columns) {
        List<Column<T, ?>> currentColumns = getColumns();
        Set<Column<T, ?>> removeColumns = new HashSet<>(currentColumns);
        Set<Column<T, ?>> addColumns = Arrays.stream(columns)
                .collect(Collectors.toSet());

        removeColumns.removeAll(addColumns);
        removeColumns.stream().forEach(this::removeColumn);

        addColumns.removeAll(currentColumns);
        addColumns.stream().forEach(c -> addColumn(getIdentifier(c), c));

        setColumnOrder(columns);
    }