@Factory
    public static Matcher<ListView> hasListCell(Object value) {
        String descriptionText = "has list cell \"" + value + "\"";
        return typeSafeMatcher(ListView.class, descriptionText, ListViewMatchers::getItemsString,
            node -> hasListCell(node, value));
    }