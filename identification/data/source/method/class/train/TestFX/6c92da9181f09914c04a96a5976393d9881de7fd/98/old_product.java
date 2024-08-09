@Factory
    public static Matcher<ListView> hasVisiblePlaceholder(Node placeHolder) {
        String descriptionText = "has " + getPlaceHolderDescription(placeHolder, true);
        return typeSafeMatcher(ListView.class, descriptionText,
            listView -> getPlaceHolderDescription(listView.getPlaceholder(), true),
            node -> hasVisiblePlaceholder(node, placeHolder));
    }