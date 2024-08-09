public static Matcher<Labeled> hasText(String text) {
        String descriptionText = "has text \"" + text + "\"";
        return typeSafeMatcher(Labeled.class, descriptionText,
            labeled -> "\"" + labeled.getText() + "\"",
            labeled -> Objects.equals(text, labeled.getText()));
    }