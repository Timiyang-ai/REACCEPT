public void destroy() {
		if (lwjglApplet != null) {
			lwjglApplet.destroy();
		}
		
		progressbar = null;
		logo 		= null;
		
		logoBuffer = null;
		progressbarBuffer = null;
	}