@Factory
    public static Matcher<TableView> containsRow(Object...cells) {
        String descriptionText = "has row: " + Arrays.toString(cells);
        return typeSafeMatcher(TableView.class, descriptionText, TableViewMatchers::toText,
            node -> containsRow(node, cells));
    }