public Tile getParent() {
		if (this.zoomLevel == 0) {
			return null;
		}

		return new Tile(this.tileX / 2, this.tileY / 2, (byte) (this.zoomLevel - 1), this.tileSize);
	}