public void setColumnOrder(Column<T, ?>... columns) {
        setColumnOrder(Stream.of(columns));
    }