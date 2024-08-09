@Factory
    public static <T> Matcher<Node> containsItems(T... items) {
        String descriptionText = "contains items " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, node -> containsItems(node, items));
    }