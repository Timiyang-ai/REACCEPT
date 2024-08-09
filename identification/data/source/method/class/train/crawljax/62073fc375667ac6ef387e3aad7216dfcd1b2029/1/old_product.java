@Override
	public String getDomWithoutIframeContent() {
		try {
			String dom = browser.getPageSource();
			String result = toUniformDOM(dom);
			return result;
		} catch (WebDriverException e) {
			throwIfConnectionException(e);
			return "";
		}
	}