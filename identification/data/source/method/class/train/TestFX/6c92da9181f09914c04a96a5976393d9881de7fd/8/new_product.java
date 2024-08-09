@Factory
    public static Matcher<Parent> hasChildren(int amount) {
        String descriptionText = "has " + amount + " children";
        return typeSafeMatcher(Parent.class, descriptionText,
            parent -> parent.getChildrenUnmodifiable().size() == amount);
    }