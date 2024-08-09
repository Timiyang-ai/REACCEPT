@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasTableCell(Object value) {
        String descriptionText = "has table cell \"" + value + "\"";
        return typeSafeMatcher(TableView.class, descriptionText,
            node -> hasTableCell(node, value));
    }