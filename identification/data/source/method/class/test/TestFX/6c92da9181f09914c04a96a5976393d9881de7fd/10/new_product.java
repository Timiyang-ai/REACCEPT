@Factory
    public static Matcher<Node> hasListCell(Object value) {
        String descriptionText = "has list cell \"" + value + "\"";
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasListCell(node, value));
    }