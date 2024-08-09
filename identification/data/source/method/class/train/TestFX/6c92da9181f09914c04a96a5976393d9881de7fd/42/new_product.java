@Factory
    public static Matcher<TextFlow> hasText(String string) {
        String descriptionText = "has text \"" + string + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText,
            textFlow -> "TextFlow containing text: \"" + getText(textFlow) + "\"",
            node -> hasText(node, string));
    }