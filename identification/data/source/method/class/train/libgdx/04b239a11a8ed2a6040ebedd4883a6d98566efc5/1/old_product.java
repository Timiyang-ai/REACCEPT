public void render () {
		batch.setProjectionMatrix(projection);
		batch.setTransformMatrix(identity);
		batch.begin();
		root.render(batch);
		batch.end();
	}