@Factory
    public static Matcher<Node> hasChild() {
        String descriptionText = "has child";
        return typeSafeMatcher(Parent.class, descriptionText, ParentMatchers::hasChild);
    }