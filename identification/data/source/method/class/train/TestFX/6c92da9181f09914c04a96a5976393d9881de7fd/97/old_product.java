@Factory
    public static Matcher<TableView> hasTableCell(Object value) {
        String descriptionText = "has table cell \"" + value + "\"";
        return typeSafeMatcher(TableView.class, descriptionText,
            tableView -> toText(tableView) + "\nwhich does not contain a cell with the given value",
            node -> hasTableCell(node, value));
    }