@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasChild() {
        String descriptionText = "has child";
        return typeSafeMatcher(Parent.class, descriptionText, node -> hasChild(node));
    }