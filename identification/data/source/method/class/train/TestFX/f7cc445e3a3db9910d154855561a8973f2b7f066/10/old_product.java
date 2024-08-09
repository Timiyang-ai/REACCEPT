@Factory
    public static Matcher<Node> isNotFocused() {
        return baseMatcher("Node does not have focus", node -> !node.isFocused());
    }