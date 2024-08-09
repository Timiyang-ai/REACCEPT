@Factory
    public static Matcher<Text> hasText(Matcher<String> matcher) {
        String descriptionText = "has " + matcher.toString();
        return typeSafeMatcher(Text.class, descriptionText,
            text -> text.getClass().getSimpleName() + " with text: \"" + text.getText() + "\"",
            textNode -> matcher.matches(textNode.getText()));
    }