@Factory
    public static Matcher<ComboBox> hasItems(int amount) {
        String descriptionText = "has " + amount + " items";
        return typeSafeMatcher(ComboBox.class, descriptionText,
            comboBox -> String.valueOf(comboBox.getItems().size()),
            node -> hasItems(node, amount));
    }