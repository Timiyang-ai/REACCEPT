public void start() {
		if(loaderThread == null && !fatalError) {
			loaderThread = new Thread(this);
			loaderThread.setName("AppletLoader.loaderThread");
			loaderThread.start();
			
			animationThread = new Thread() {
				public void run() {
					while(state != STATE_DONE) {
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