@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasColoredText(String string) {
        String descriptionText = "has colored text \"" + string + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText, node -> hasColoredText(node, string, false));
    }