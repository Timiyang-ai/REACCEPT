@Override
	public synchronized boolean fireEventAndWait(Eventable eventable)
	        throws ElementNotVisibleException,
	        NoSuchElementException, InterruptedException {
		try {

			boolean handleChanged = false;
			boolean result = false;

			if (eventable.getRelatedFrame() != null && !eventable.getRelatedFrame().equals("")) {
				LOGGER.debug("switching to frame: " + eventable.getRelatedFrame());
				try {

					switchToFrame(eventable.getRelatedFrame());
				} catch (NoSuchFrameException e) {
					LOGGER.debug("Frame not found, possibily while back-tracking..", e);
					// TODO Stefan, This exception is catched to prevent stopping
					// from working
					// This was the case on the Gmail case; find out if not switching
					// (catching)
					// Results in good performance...
				}
				handleChanged = true;
			}

			WebElement webElement =
			        browser.findElement(eventable.getIdentification().getWebDriverBy());

			if (webElement != null) {
				result = fireEventWait(webElement, eventable);
			}

			if (handleChanged) {
				browser.switchTo().defaultContent();
			}
			return result;
		} catch (ElementNotVisibleException | NoSuchElementException e) {
			throw e;
		} catch (WebDriverException e) {
			throwIfConnectionException(e);
			return false;
		}
	}