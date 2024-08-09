public void zoom(byte zoomLevelDiff) {
		synchronized (this) {
			setZoomLevelInternal(getZoomLevel() + zoomLevelDiff);
		}
		notifyObservers();
	}