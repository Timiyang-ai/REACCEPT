public void zoomOut() {
		synchronized (this) {
			byte currentZoomLevel = getZoomLevel();
			if (currentZoomLevel > 0) {
				setZoomLevelInternal((byte) (currentZoomLevel - 1));
			}
		}
		notifyObservers();
	}