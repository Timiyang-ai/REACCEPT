public static Matcher<Text> hasFontSmoothingType(FontSmoothingType smoothingType) {
        String descriptionText = "has font smoothing type: \"" + smoothingType + "\"";
        return typeSafeMatcher(Text.class, descriptionText,
            textNode -> textNode.getClass().getSimpleName() + " with font smoothing type: \"" +
                    textNode.getFontSmoothingType() + "\"",
            textNode -> Objects.equals(smoothingType, textNode.getFontSmoothingType()));
    }