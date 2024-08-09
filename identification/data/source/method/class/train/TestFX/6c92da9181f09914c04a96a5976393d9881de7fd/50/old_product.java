@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasItems(int amount) {
        String descriptionText = "has " + amount + " items";
        return typeSafeMatcher(ComboBox.class, descriptionText, node -> hasItems(node, amount));
    }