public static <T> Matcher<ComboBox> hasSelectedItem(T selection) {
        String descriptionText = String.format("has selection \"%s\"", selection);
        return typeSafeMatcher(ComboBox.class, descriptionText,
            comboBox -> "\"" + comboBox.getSelectionModel().getSelectedItem().toString() + "\"",
            comboBox -> hasSelectedItem(comboBox, selection));
    }