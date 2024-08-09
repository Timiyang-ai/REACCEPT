public void stepWithRows(Consumer<Row[]> rowConsumer, int n) {
        if (isEmpty()) {
            return;
        }
        Row[] rows = new Row[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new Row(this);
        }

        int max = rowCount() - n;
        for (int i = 0; i <= max; i++) {
            for (int r = 0; r < n; r++) {
                rows[r].at(i + r);
            }
            rowConsumer.accept(rows);
        }
    }