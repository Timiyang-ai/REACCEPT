@Factory
    public static Matcher<Text> hasText(String text) {
        String descriptionText = "has text \"" + text + "\"";
        return typeSafeMatcher(Text.class, descriptionText,
            textNode -> textNode.getClass().getSimpleName() + " with text: \"" + textNode.getText() + "\"",
            textNode -> Objects.equals(text, textNode.getText()));
    }