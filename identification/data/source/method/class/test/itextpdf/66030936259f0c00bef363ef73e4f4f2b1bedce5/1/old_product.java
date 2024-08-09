public Map<String, String> parseBorder(final String border) {
		HashMap<String, String> map = new HashMap<String, String>(0);
		String split[] = border.split("\\s");
		int length = split.length;
		if (length == 1) {
			if (borderwidth.contains(split[0]) || isNumericValue(split[0]) || isMetricValue(split[0])) {
				map.putAll(parseBoxValues(split[0], BORDER2, WIDTH));
			} else {
				map.putAll(parseBoxValues(split[0], BORDER2, STYLE));
			}
		} else {
			for(int i = 0 ; i<length ; i++) {
				String value = split[i];
				if (borderwidth.contains(value) || isNumericValue(value) || isMetricValue(value)) {
					map.putAll(parseBoxValues(value, BORDER2, WIDTH));
				} else if(borderstyle.contains(value)){
					map.putAll(parseBoxValues(value, BORDER2, STYLE));
				} else if(value.contains("rgb(") || value.contains("#") || WebColors.NAMES.containsKey(value.toLowerCase())){
					map.putAll(parseBoxValues(value, BORDER2, COLOR));
				}
			}
		}
		return map;
	}