public void dispose () {
		if (ownsTexture) {
			for (int i = 0; i < regions.length; i++)
				regions[i].getTexture().dispose();
		}
	}