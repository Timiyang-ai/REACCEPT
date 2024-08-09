public void setColumnOrder(Column<?, T>... orderedColumns) {
        ColumnConfiguration conf = getEscalator().getColumnConfiguration();

        // Trigger ComplexRenderer.destroy for old content
        conf.removeColumns(0, conf.getColumnCount());

        List<Column<?, T>> newOrder = new ArrayList<Column<?, T>>();
        if (selectionColumn != null) {
            newOrder.add(selectionColumn);
        }

        int i = 0;
        for (Column<?, T> column : orderedColumns) {
            if (columns.contains(column)) {
                newOrder.add(column);
                ++i;
            } else {
                throw new IllegalArgumentException("Given column at index " + i
                        + " does not exist in Grid");
            }
        }

        if (columns.size() != newOrder.size()) {
            columns.removeAll(newOrder);
            newOrder.addAll(columns);
        }
        columns = newOrder;

        // Do ComplexRenderer.init and render new content
        conf.insertColumns(0, columns.size());

        // Number of frozen columns should be kept same #16901
        updateFrozenColumns();

        // Update column widths.
        for (Column<?, T> column : columns) {
            column.reapplyWidth();
        }

        // Recalculate all the colspans
        for (HeaderRow row : header.getRows()) {
            row.calculateColspans();
        }
        for (FooterRow row : footer.getRows()) {
            row.calculateColspans();
        }
    }