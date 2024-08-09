public void setColumnOrder(Column<T, ?>... columns) {
        List<String> columnOrder = new ArrayList<>();
        for (Column<T, ?> column : columns) {
            if (columnSet.contains(column)) {
                columnOrder.add(column.getId());
            } else {
                throw new IllegalArgumentException(
                        "setColumnOrder should not be called "
                                + "with columns that are not in the grid.");
            }
        }

        List<String> stateColumnOrder = getState().columnOrder;
        if (stateColumnOrder.size() != columnOrder.size()) {
            stateColumnOrder.removeAll(columnOrder);
            columnOrder.addAll(stateColumnOrder);
        }

        getState().columnOrder = columnOrder;
        fireColumnReorderEvent(false);
    }