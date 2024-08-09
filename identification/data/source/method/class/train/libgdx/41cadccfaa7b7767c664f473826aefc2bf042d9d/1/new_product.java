public void dispose () {
		if (ownsTexture) region.getTexture().dispose();
	}