@Factory
    public static <T> Matcher<Node> containsExactlyItemsInOrder(T... items) {
        String descriptionText = "contains exactly items in order " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, node -> containsExactlyItemsInOrder(node, items));
    }