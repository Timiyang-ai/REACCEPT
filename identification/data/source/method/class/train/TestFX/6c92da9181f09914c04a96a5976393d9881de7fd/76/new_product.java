@Factory
    public static Matcher<TextInputControl> hasText(Matcher<String> matcher) {
        String descriptionText = "has " + matcher.toString();
        return typeSafeMatcher(TextInputControl.class, descriptionText,
            textInputControl -> textInputControl.getClass().getSimpleName() + " with text: \"" +
                    textInputControl.getText() + "\"",
            textInputControl -> matcher.matches(textInputControl.getText()));
    }