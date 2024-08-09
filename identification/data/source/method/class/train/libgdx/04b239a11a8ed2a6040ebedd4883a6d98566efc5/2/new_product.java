public void draw () {
		batch.setProjectionMatrix(projection);
		batch.setTransformMatrix(identity);
		batch.begin();
		root.draw(batch, 1);
		batch.end();
	}