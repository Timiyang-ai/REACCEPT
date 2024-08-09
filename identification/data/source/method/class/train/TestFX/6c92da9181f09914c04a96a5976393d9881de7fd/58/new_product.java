@Factory
    public static <T> Matcher<Node> containsExactlyItems(T... items) {
        String descriptionText = "contains exactly items " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, node -> containsExactlyItems(node, items));
    }