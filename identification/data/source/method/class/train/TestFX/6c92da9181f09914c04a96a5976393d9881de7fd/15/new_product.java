@Factory
    public static Matcher<ListView> hasItems(int amount) {
        String descriptionText = "has " + amount + " items";
        return typeSafeMatcher(ListView.class, descriptionText,
            listView -> String.valueOf(listView.getItems().size()),
            listView -> listView.getItems().size() == amount);
    }