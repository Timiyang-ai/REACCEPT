public static Matcher<TextFlow> hasColoredText(String coloredTextMarkup) {
        String descriptionText = "has colored text \"" + coloredTextMarkup + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText,
            textFlow -> "TextFlow with colored text: \"" + getColoredTextMarkup(textFlow, false) + "\"",
            node -> hasColoredText(node, coloredTextMarkup, false));
    }