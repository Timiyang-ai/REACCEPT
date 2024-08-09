@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasExactlyColoredText(String string) {
        String descriptionText = "has exactly colored text \"" + string + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText, node -> hasColoredText(node, string, true));
    }