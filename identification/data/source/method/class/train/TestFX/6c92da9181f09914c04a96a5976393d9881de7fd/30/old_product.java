@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> isEmpty() {
        String descriptionText = "is empty (has no items)";
        return typeSafeMatcher(ListView.class, descriptionText, ListViewMatchers::isListEmpty);
    }