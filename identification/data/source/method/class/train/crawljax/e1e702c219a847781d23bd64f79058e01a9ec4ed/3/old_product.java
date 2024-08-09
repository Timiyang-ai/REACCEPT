public static String getBaseUrl(String url) {
		String head = url.substring(0, url.indexOf(":"));
		String subLoc = url.substring(head.length() + DomUtils.BASE_LENGTH);
		return head + "://" + subLoc.substring(0, subLoc.indexOf("/"));
	}