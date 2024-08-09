public void zoomIn() {
		synchronized (this) {
			byte currentZoomLevel = getZoomLevel();
			if (currentZoomLevel < Byte.MAX_VALUE) {
				setZoomLevelInternal((byte) (currentZoomLevel + 1));
			}
		}
		notifyObservers();
	}