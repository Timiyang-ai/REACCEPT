public static String getBaseUrl(String url) {
		String head = url.substring(0, url.indexOf(':'));
		String subLoc = url.substring(head.length() + DomUtils.BASE_LENGTH);
		int index = subLoc.indexOf('/');
		String base;
		if (index == -1) {
			base = url;
		} else {
			base = head + "://" + subLoc.substring(0, index);
		}

		return base;
	}