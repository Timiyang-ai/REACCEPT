public static String getContextPath() {
		return StringUtils.isEmpty(WebConstants.WEBAPP_NAME) ? "" : "/" + WebConstants.WEBAPP_NAME;
	}