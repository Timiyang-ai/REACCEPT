@Override
	public String getStrippedDomWithoutIframeContent() {
		try {
			String dom = browser.getPageSource();
			String result = toUniformDOM(dom);
			return result;
		} catch (WebDriverException e) {
			throwIfConnectionException(e);
			return "";
		}
	}