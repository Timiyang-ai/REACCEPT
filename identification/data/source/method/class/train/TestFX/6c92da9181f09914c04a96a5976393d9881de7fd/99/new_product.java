@Factory
    public static Matcher<Node> hasPlaceholder(Node placeHolder) {
        String descriptionText = "has ";
        // better description messages for Labeled nodes
        if (Labeled.class.isAssignableFrom(placeHolder.getClass())) {
            descriptionText += "labeled placeholder containing text: \"" +
                    ((Labeled) placeHolder).getText() + "\"";
        } else {
            descriptionText += "placeholder " + placeHolder;
        }
        return typeSafeMatcher(ListView.class, descriptionText, node -> hasPlaceholder(node, placeHolder));
    }