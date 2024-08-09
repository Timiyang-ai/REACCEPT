@Factory
    public static Matcher<Text> hasFont(Font font) {
        String descriptionText = "has font " + toText(font);
        return typeSafeMatcher(Text.class, descriptionText,
            textNode -> textNode.getClass().getSimpleName() + " with font: " + toText(textNode.getFont()),
            textNode -> Objects.equals(font, textNode.getFont()));
    }