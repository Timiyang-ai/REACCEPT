public static Matcher<Node> anything() {
        return baseMatcher("anything", node -> true);
    }