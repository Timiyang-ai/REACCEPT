public static Matcher<MenuItem> hasText(Matcher<String> matcher) {
        String descriptionText = "has " + matcher.toString();
        return typeSafeMatcher(MenuItem.class, descriptionText,
            menuItem -> "\"" + menuItem.getText() + "\"",
            menuItem -> matcher.matches(menuItem.getText()));
    }