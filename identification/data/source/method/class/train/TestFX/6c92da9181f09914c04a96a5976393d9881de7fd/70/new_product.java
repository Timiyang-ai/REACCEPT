public static Matcher<TableView> containsRow(Object...cells) {
        String descriptionText = "has row: " + Arrays.toString(cells);
        return typeSafeMatcher(TableView.class, descriptionText, TableViewMatchers::toText,
            tableView -> containsRow(tableView, cells));
    }