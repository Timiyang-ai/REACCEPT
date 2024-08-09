@Factory
    public static <T> Matcher<Node> containsItemsInOrder(T... items) {
        String descriptionText = "contains items in order " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, node -> containsItemsInOrder(node, items));
    }