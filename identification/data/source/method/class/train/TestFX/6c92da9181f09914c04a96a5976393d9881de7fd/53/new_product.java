@Factory
    public static Matcher<ListView> isEmpty() {
        String descriptionText = "is empty (contains no items)";
        return typeSafeMatcher(ListView.class, descriptionText,
            listView -> listView.getItems().size() == 0 ? "empty" : "contains " + listView.getItems().size() + " items",
            listView -> listView.getItems().isEmpty());
    }