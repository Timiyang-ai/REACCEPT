@Factory
    public static Matcher<Text> isUnderlined(boolean underlined) {
        String descriptionText = (underlined ? "is " : "is not ") + "underlined";
        return typeSafeMatcher(Text.class, descriptionText,
            textNode -> textNode.getClass().getSimpleName() + (textNode.isUnderline() ?
                    " with " : " without ") + "underline",
            textNode -> textNode.isUnderline() == underlined);
    }