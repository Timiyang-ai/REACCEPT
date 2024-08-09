@Factory
    public static Matcher<TextFlow> hasExactlyColoredText(String coloredTextMarkup) {
        String descriptionText = "has exactly colored text \"" + coloredTextMarkup + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText, textFlow -> {
            for (Node child : textFlow.getChildren()) {
                if (Text.class.isAssignableFrom(child.getClass())) {
                    Text text = (Text) child;
                    String textColor = text.getFill().toString().substring(2, 8);
                    if (!ColorUtils.getNamedColor(textColor).isPresent()) {
                        return "impossible to exactly match TextFlow containing colored text: \"" +
                                ((Text) child).getText() + "\" which has color: \"" + textColor + "\".\n" +
                                "This is not a named color. The closest named color is: \"" +
                                getClosestNamedColor(textColor).toUpperCase(Locale.US) + "\".\nSee: " +
                                "https://docs.oracle.com/javase/9/docs/api/javafx/scene/doc-files" +
                                "/cssref.html#typecolor";
                    }
                }
            }
            return getColoredTextMarkup(textFlow, true);
        },
            node -> hasColoredText(node, coloredTextMarkup, true));
    }