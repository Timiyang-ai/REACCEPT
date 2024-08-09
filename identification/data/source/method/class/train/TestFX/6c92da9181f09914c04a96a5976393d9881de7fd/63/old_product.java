@Factory
    public static Matcher<Parent> hasChild() {
        String descriptionText = "has child";
        return typeSafeMatcher(Parent.class, descriptionText,
            parent -> !parent.getChildrenUnmodifiable().isEmpty());
    }