public void dispose () {
		for (int i = 0, n = textures.size(); i < n; i++)
			textures.get(i).dispose();
		textures.clear();
	}