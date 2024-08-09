@Factory
    public static <T> Matcher<ComboBox> containsExactlyItems(T... items) {
        String descriptionText = "contains exactly items " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, ComboBoxMatchers::getItemsString,
            comboBox -> containsExactlyItems(comboBox, items));
    }