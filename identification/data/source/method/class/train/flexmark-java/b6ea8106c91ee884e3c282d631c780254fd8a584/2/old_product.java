public void insertColumns(int column, int count) {
            if (count <= 0) return;

            int totalColumns = this.getTotalColumns();

            if (column >= totalColumns) {
                // append to the end
                appendColumns(count);
            } else {
                // insert in the middle
                IndexSpanOffset indexSpan = indexOf(column);
                int index = indexSpan.index;
                int spanOffset = indexSpan.spanOffset;

                if (spanOffset > 0 && index < cells.size()) {
                    // spanning column, we expand its span
                    TableCell cell = cells.get(index);

                    if (cell.columnSpan == 0) throw new IllegalStateException("TableRow.insertColumns must be called only after 0-span dummy columns have been removed by calling cleanup() on table, section or row");

                    cells.remove(index);
                    cells.add(index, cell.withColumnSpan(cell.columnSpan + count));
                } else {
                    TableCell cell = cells.get(index);
                    if (cell.columnSpan == 0) throw new IllegalStateException("TableRow.insertColumns must be called only after 0-span dummy columns have been removed by calling cleanup() on table, section or row");

                    for (int i = 0; i < count; i++) {
                        addColumn(index);
                    }
                }
            }
        }