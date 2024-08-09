public static Matcher<Node> hasChild(String query) {
        String descriptionText = "Node has child \"" + query + "\"";
        return baseMatcher(descriptionText, node -> hasChild(node, query));
    }