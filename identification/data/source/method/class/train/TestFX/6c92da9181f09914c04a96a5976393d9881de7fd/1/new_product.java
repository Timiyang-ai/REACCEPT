public static <T> Matcher<ComboBox> containsExactlyItemsInOrder(T... items) {
        String descriptionText = "contains exactly items in order " + Arrays.toString(items);
        return typeSafeMatcher(ComboBox.class, descriptionText, ComboBoxMatchers::getItemsString,
            comboBox -> containsExactlyItemsInOrder(comboBox, items));
    }