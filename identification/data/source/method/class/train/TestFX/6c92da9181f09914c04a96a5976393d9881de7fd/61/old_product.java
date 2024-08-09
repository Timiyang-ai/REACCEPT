@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> containsRowAtIndex(int rowIndex, Object...cells) {
        String descriptionText = "has row: " + Arrays.toString(cells);
        return typeSafeMatcher(TableView.class, descriptionText, node -> containsRowAtIndex(node, rowIndex, cells));
    }