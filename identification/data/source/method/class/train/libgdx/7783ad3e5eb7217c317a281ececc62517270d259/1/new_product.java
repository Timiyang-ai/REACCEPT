public void dispose () {
		for (Texture texture : textures)
			texture.dispose();
		textures.clear(0);
	}