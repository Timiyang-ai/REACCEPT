@Factory
    public static <T> Matcher<ComboBox> hasSelectedItem(T selection) {
        String descriptionText = "has selection " + selection;
        return typeSafeMatcher(ComboBox.class, descriptionText,
            comboBox -> comboBox.getSelectionModel().getSelectedItem().toString(),
            node -> hasSelectedItem(node, selection));
    }