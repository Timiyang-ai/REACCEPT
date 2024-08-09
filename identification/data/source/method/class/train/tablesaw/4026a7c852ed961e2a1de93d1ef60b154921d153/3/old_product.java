public void stepWithRows(Consumer<Row[]> rowConsumer, int n) {
    if (isEmpty()) {
      return;
    }
    Row[] rows = new Row[n];
    for (int i = 0; i < n; i++) {
      rows[i] = new Row(this);
    }

    int max = rowCount() / n;

    for (int i = 0; i < max; i++) { // 0, 1
      for (int r = 1; r <= n; r++) {
        int row = i * n + r - 1;
        rows[r - 1].at(row);
      }
      rowConsumer.accept(rows);
    }
  }