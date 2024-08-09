public void deleteColumns(int column, int count) {
            int remaining = count;
            IndexSpanOffset indexSpan = indexOf(column);
            int index = indexSpan.index;
            int spanOffset = indexSpan.spanOffset;

            while (index < cells.size() && remaining > 0) {
                TableCell cell = cells.get(index);
                cells.remove(index);

                if (cell.columnSpan == 0) throw new IllegalStateException("TableRow.deleteColumns must be called only after 0-span dummy columns have been removed by calling cleanup() on table, section or row");

                if (spanOffset > 0) {
                    // inside the first partial span, truncate it to offset or reduce by remaining
                    if (cell.columnSpan - spanOffset > remaining) {
                        cells.add(index, cell.withColumnSpan(cell.columnSpan - remaining));
                        break;
                    } else {
                        // reinsert with reduced span
                        cells.add(index, cell.withColumnSpan(spanOffset));
                        index++;
                    }
                } else if (cell.columnSpan - spanOffset > remaining) {
                    cells.add(index, cell.withColumnSpan(cell.columnSpan - remaining));
                    break;
                }

                remaining -= cell.columnSpan - spanOffset;
                spanOffset = 0;
            }
        }