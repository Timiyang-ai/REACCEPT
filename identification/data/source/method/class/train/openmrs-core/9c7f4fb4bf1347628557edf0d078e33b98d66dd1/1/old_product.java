public static String getContextPath() {
		return webappNameIsEmpty() ? "" : "/" + WebConstants.WEBAPP_NAME;
	}