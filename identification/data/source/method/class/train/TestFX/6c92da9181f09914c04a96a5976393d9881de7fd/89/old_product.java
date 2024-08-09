@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasText(Matcher<String> matcher) {
        String descriptionText = "has " + matcher.toString();
        return typeSafeMatcher(TextInputControl.class, descriptionText,
            node -> hasText(node, matcher));
    }