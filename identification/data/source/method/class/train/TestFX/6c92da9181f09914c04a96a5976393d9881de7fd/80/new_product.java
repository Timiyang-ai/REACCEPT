public static <T> Matcher<ComboBox> containsItems(T... items) {
        String descriptionText = "contains items " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, ComboBoxMatchers::getItemsString,
            comboBox -> containsItems(comboBox, items));
    }