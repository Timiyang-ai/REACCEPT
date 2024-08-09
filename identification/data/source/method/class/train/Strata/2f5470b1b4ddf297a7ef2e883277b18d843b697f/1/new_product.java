@Override
  protected List<Class<?>> getColumnTypes(TradeReport report) {
    List<Class<?>> columnTypes = new ArrayList<Class<?>>(report.getColumnHeaders().length);
    for (int c = 0; c < report.getColumnHeaders().length; c++) {
      Class<?> type = null;
      for (int r = 0; r < report.getData().length; r++) {
        Result<?> cell = report.getData()[r][c];
        if (cell.isFailure()) {
          continue;
        }
        type = cell.getValue().getClass();
        break;
      }
      columnTypes.add(type);
    }
    return columnTypes;
  }