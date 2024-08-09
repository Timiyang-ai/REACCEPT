public static Matcher<Labeled> hasText(Matcher<String> matcher) {
        String descriptionText = "has " + matcher.toString();
        return typeSafeMatcher(Labeled.class, descriptionText,
            labeled -> "\"" + labeled.getText() + "\"",
            labeled -> matcher.matches(labeled.getText()));
    }