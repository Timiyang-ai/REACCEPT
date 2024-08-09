@Factory
    public static Matcher<TextFlow> hasExactlyColoredText(String coloredTextMarkup) {
        String descriptionText = "has exactly colored text \"" + coloredTextMarkup + "\"";
        return typeSafeMatcher(TextFlow.class, descriptionText, textFlow -> {
            for (Node child : textFlow.getChildren()) {
                if (Text.class.isAssignableFrom(child.getClass())) {
                    Text text = (Text) child;
                    Paint fill = text.getFill();
                    if (Color.class.isAssignableFrom(fill.getClass())) {
                        String textColor = fill.toString().substring(2, 8);
                        if (!ColorUtils.getNamedColor((Color) fill).isPresent()) {
                            return "impossible to exactly match TextFlow containing colored text: \"" +
                                    ((Text) child).getText() + "\" which has color: \"#" + textColor + "\".\n" +
                                    "This is not a named color. The closest named color is: \"" +
                                    ColorUtils.getClosestNamedColor(Integer.parseInt(textColor, 16)) + "\".\nSee: " +
                                    "https://docs.oracle.com/javase/9/docs/api/javafx/scene/doc-files" +
                                    "/cssref.html#typecolor";
                        }
                    } else {
                        return "exact color matching for subclasses of javafx.scene.paint.Paint besides " +
                                "javafx.scene.paint.Color is not (yet) supported.";
                    }
                }
            }
            return getColoredTextMarkup(textFlow, true);
        }, node -> hasColoredText(node, coloredTextMarkup, true));
    }