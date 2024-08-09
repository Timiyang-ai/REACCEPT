@Factory
    public static Matcher<Parent> hasChild() {
        String descriptionText = "has at least one child";
        return typeSafeMatcher(Parent.class, descriptionText,
            parent -> toText(parent.getChildrenUnmodifiable()),
            parent -> !parent.getChildrenUnmodifiable().isEmpty());
    }