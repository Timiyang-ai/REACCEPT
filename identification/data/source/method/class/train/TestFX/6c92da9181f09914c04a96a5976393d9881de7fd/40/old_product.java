@Factory
    public static Matcher<TableView> hasTableCell(Object value) {
        String descriptionText = "has table cell \"" + value + "\"";
        return typeSafeMatcher(TableView.class, descriptionText, TableViewMatchers::toText,
            tableView -> hasTableCell(tableView, value));
    }