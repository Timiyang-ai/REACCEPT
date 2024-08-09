@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasText(String string) {
        String descriptionText = "has text \"" + string + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText, node -> hasText(node, string));
    }