public static int getColor(GraphicFactory graphicFactory, String colorString, ThemeCallback themeCallback, RenderInstruction origin) {
        if (colorString.isEmpty() || colorString.charAt(0) != '#') {
            throw new IllegalArgumentException(UNSUPPORTED_COLOR_FORMAT + colorString);
        } else if (colorString.length() == 7) {
            return getColor(graphicFactory, colorString, 255, 1, themeCallback, origin);
        } else if (colorString.length() == 9) {
            return getColor(graphicFactory, colorString, Integer.parseInt(colorString.substring(1, 3), 16), 3, themeCallback, origin);
        } else {
            throw new IllegalArgumentException(UNSUPPORTED_COLOR_FORMAT + colorString);
        }
    }