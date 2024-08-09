public static Matcher<MenuItem> hasText(String text) {
        String descriptionText = "has text \"" + text + "\"";
        return typeSafeMatcher(MenuItem.class, descriptionText,
            menuItem -> "\"" + menuItem.getText() + "\"",
            menuItem -> Objects.equals(text, menuItem.getText()));
    }