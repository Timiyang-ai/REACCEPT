public synchronized void dispose () {
		for (Page page : pages) {
			page.image.dispose();
		}
		disposed = true;
	}