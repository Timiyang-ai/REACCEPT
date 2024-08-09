public static Matcher<ListView> hasSelectedRow(Object value) {
        String descriptionText = String.format("has selection \"%s\"", value);
        return typeSafeMatcher(ListView.class, descriptionText,
            listView -> "\"" + listView.getSelectionModel().getSelectedItems().toString() + "\"",
            listView -> hasSelectedItem(listView, value));
    }