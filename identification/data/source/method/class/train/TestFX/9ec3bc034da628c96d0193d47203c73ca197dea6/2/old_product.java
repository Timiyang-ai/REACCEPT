@Factory
    public static Matcher<TableView> hasNumRows(int rows) {
        String descriptionText = "has " + rows + " rows";
        return typeSafeMatcher(TableView.class, descriptionText,
            tableView -> String.valueOf(tableView.getItems().size()),
            node -> hasNumRows(node, rows));
    }