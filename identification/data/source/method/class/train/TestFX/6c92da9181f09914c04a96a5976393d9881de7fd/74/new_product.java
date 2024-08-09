public static Matcher<TableView> containsRowAtIndex(int rowIndex, Object... cells) {
        String descriptionText = String.format("has row: %s at index %d", Arrays.toString(cells), rowIndex);
        return typeSafeMatcher(TableView.class, descriptionText,
            tableView -> {
                if (rowIndex < 0) {
                    return "given negative row index: " + rowIndex;
                } else if (rowIndex >= tableView.getItems().size()) {
                    return "given out-of-bounds row index: " + rowIndex +
                            " (table only has " + tableView.getItems().size() + " rows)";
                } else {
                    return toText(tableView, rowIndex) + " at index: " + rowIndex;
                }
            },
            tableView -> containsRowAtIndex(tableView, rowIndex, cells));
    }