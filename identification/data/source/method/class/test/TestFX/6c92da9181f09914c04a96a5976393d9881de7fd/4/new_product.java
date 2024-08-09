@Factory
    public static Matcher<Node> containsRow(Object...cells) {
        String descriptionText = "has row: " + Arrays.toString(cells);
        return typeSafeMatcher(TableView.class, descriptionText, node -> containsRow(node, cells));
    }