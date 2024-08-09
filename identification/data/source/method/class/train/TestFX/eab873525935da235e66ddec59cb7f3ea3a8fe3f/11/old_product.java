@Factory
    @Unstable(reason = "maybe find a better method name")
    public static Matcher<Node> anything() {
        return baseMatcher("anything", node -> true);
    }