public static Matcher<Node> hasChildren(int amount, String query) {
        String descriptionText = "Node has " + amount + " children \"" + query + "\"";
        return baseMatcher(descriptionText, node -> hasChildren(node, amount, query));
    }