public void zoom(byte zoomLevelDiff) {
		synchronized (this) {
			setZoomLevelInternal(this.zoomLevel + zoomLevelDiff);
		}
		notifyObservers();
	}