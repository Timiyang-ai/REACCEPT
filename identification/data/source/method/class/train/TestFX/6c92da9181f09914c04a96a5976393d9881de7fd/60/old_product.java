@Factory
    public static Matcher<Parent> hasChildren(int amount) {
        String descriptionText = "has exactly " + amount + " children";
        return typeSafeMatcher(Parent.class, descriptionText,
            parent -> toText(parent.getChildrenUnmodifiable()),
            parent -> parent.getChildrenUnmodifiable().size() == amount);
    }