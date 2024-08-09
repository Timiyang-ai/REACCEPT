@Factory
    @Unstable(reason = "is missing apidocs")
    public static Matcher<Node> hasVisiblePlaceholder(Node placeHolder) {
        String descriptionText = "has visible";
        // better description messages for Labeled nodes
        if (Labeled.class.isAssignableFrom(placeHolder.getClass())) {
            descriptionText += "labeled placeholder containing text: \"" +
                    ((Labeled) placeHolder).getText() + "\"";
        } else {
            descriptionText += "placeholder " + placeHolder;
        }
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasVisiblePlaceholder(node, placeHolder));
    }