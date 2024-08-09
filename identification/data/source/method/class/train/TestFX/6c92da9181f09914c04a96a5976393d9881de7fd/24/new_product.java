@Factory
    public static Matcher<Node> hasChildren(int amount) {
        String descriptionText = "has " + amount + " children";
        return typeSafeMatcher(Parent.class, descriptionText, node -> hasChildren(amount, node));
    }