@Factory
    public static Matcher<Node> hasText(Matcher<String> matcher) {
        String descriptionText = "has " + matcher.toString();
        return typeSafeMatcher(Text.class, descriptionText, node -> hasText(node, matcher));
    }