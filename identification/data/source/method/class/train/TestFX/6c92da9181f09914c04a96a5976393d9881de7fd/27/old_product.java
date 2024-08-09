@Factory
    public static Matcher<TextInputControl> hasText(String text) {
        String descriptionText = "has text \"" + text + "\"";
        return typeSafeMatcher(TextInputControl.class, descriptionText,
            textInputControl -> textInputControl.getClass().getSimpleName() + " with text: \"" +
                    textInputControl.getText() + "\"",
            node -> hasText(node, text));
    }