public static Matcher<Text> hasStrikethrough(boolean strikethrough) {
        String descriptionText = (strikethrough ? "has " : "does not have ") + "strikethrough";
        return typeSafeMatcher(Text.class, descriptionText,
            textNode -> textNode.getClass().getSimpleName() + (textNode.isStrikethrough() ?
                    " with " : " without ") + "strikethrough",
            textNode -> textNode.isStrikethrough() == strikethrough);
    }