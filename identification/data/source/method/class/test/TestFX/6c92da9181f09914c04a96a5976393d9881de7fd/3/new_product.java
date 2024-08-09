@Factory
    public static Matcher<ListView> hasPlaceholder(Node placeHolder) {
        String descriptionText = "has " + getPlaceHolderDescription(placeHolder, false);
        return typeSafeMatcher(ListView.class, descriptionText,
            listView -> getPlaceHolderDescription(listView.getPlaceholder(), false),
            node -> hasPlaceholder(node, placeHolder));
    }