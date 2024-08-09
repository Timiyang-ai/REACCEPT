public static int getColor(GraphicFactory graphicFactory, String colorString) {
		if (!colorString.isEmpty() && colorString.charAt(0) == '#') {
			if (colorString.length() == 7) {
				return getColor(graphicFactory, colorString, 255, 1);
			} else if (colorString.length() == 9) {
				return getColor(graphicFactory, colorString, Integer.parseInt(colorString.substring(1, 3), 16), 3);
			}
		}
		LOGGER.warning(UNSUPPORTED_COLOR_FORMAT + colorString);
		return getColor(graphicFactory, "#000000", 255, 1);
	}