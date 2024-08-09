@Factory
    public static Matcher<ListView> isEmpty() {
        String descriptionText = "is empty (contains 0 (zero) items)";
        return typeSafeMatcher(ListView.class, descriptionText,
            listView -> "contains " + listView.getItems().size() + " items",
            listView -> listView.getItems().isEmpty());
    }