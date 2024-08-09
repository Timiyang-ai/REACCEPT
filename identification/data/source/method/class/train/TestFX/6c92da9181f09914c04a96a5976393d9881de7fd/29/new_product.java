@Factory
    public static <T> Matcher<ComboBox> containsItemsInOrder(T... items) {
        String descriptionText = "contains items in order " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, ComboBoxMatchers::getItemsString,
            comboBox -> containsItemsInOrder(comboBox, items));
    }