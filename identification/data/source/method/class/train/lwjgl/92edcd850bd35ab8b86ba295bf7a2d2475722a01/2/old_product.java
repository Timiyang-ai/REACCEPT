public void start() {
		if (lwjglApplet != null) {
			lwjglApplet.start();
		}
		else {
			if(loaderThread == null && !fatalError) {
				loaderThread = new Thread(this);
				loaderThread.setName("AppletLoader.loaderThread");
				loaderThread.start();

				animationThread = new Thread() {
					public void run() {
						while(loaderThread != null) {
							repaint();
							AppletLoader.this.sleep(100);
						}
						animationThread = null;
					}
				};
				animationThread.setName("AppletLoader.animationthread");
				animationThread.start();
			}
		}
	}