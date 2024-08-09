@Factory
    public static Matcher<Node> isFocused() {
        return baseMatcher("Node has focus", Node::isFocused);
    }