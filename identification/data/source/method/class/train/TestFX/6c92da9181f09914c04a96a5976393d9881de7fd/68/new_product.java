@Factory
    public static Matcher<Labeled> hasText(String text) {
        String descriptionText = "has text \"" + text + "\"";
        return typeSafeMatcher(Labeled.class, descriptionText,
            labeled -> "\"" + labeled.getText() + "\"",
            node -> hasText(node, text));
    }