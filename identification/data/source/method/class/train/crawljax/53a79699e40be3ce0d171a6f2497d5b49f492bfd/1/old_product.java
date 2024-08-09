@Override
	public FormInput getInputWithRandomValue(FormInput input) {

		WebElement webElement;
		try {
			webElement = browser.findElement(input.getIdentification().getWebDriverBy());
			if (!webElement.isDisplayed()) {
				return null;
			}
		} catch (WebDriverException e) {
			throwIfConnectionException(e);
			return null;
		}

		Set<InputValue> values = new HashSet<>();
		try {
			setRandomValues(input, webElement, values);
		} catch (WebDriverException e) {
			throwIfConnectionException(e);
			return null;
		}
		if (values.isEmpty()) {
			return null;
		}
		input.setInputValues(values);
		return input;

	}