public static Matcher<TableView> hasNumRows(int rows) {
        String descriptionText = "has " + rows + " rows";
        return typeSafeMatcher(TableView.class, descriptionText,
            tableView -> "contained " + (tableView.getItems().isEmpty() ? "no" : String.valueOf(
                    tableView.getItems().size()) + ' ' + (tableView.getItems().size() == 1 ? "row" : "rows")),
            tableView -> tableView.getItems().size() == rows);
    }