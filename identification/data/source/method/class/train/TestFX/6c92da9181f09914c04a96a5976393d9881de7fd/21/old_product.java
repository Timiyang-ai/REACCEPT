@Factory
    @Unstable(reason = "is missing apidocs")
    public static <T> Matcher<Node> hasSelectedItem(T selection) {
        String descriptionText = "has selection " + selection;
        return typeSafeMatcher(ComboBox.class, descriptionText, node -> hasSelectedItem(node, selection));
    }