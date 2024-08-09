@Factory
    public static Matcher<Node> hasText(String string) {
        String descriptionText = "has text \"" + string + "\"";
        return typeSafeMatcher(TextInputControl.class, descriptionText,
            node -> hasText(node, string));
    }