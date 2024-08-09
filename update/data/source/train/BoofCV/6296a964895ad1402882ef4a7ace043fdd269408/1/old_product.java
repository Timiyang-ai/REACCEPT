public void process() {
		Webcam webcam = UtilWebcamCapture.openDefault(desiredWidth,desiredHeight);

		// adjust the window size and let the GUI know it has changed
		Dimension actualSize = webcam.getViewSize();
		setPreferredSize(actualSize);
		setMinimumSize(actualSize);
		window.setMinimumSize(actualSize);
		window.setPreferredSize(actualSize);
		window.setVisible(true);

		// create
		T input = tracker.getImageType().createImage(actualSize.width,actualSize.height);

		workImage = new BufferedImage(input.getWidth(),input.getHeight(),BufferedImage.TYPE_INT_RGB);

		while( true ) {
			BufferedImage buffered = webcam.getImage();
			ConvertBufferedImage.convertFrom(webcam.getImage(),input,true);

			// mode is read/written to by the GUI also
			int mode = this.mode;

			boolean success = false;
			if( mode == 2 ) {
				RectangleCorner2D_F64 rect = new RectangleCorner2D_F64();
				rect.set(point0.x, point0.y, point1.x, point1.y);
				UtilPolygons2D_F64.convert(rect, target);
				success = tracker.initialize(input,target);
				this.mode = success ? 3 : 0;
			} else if( mode == 3 ) {
				success = tracker.process(input,target);
			}

			synchronized( workImage ) {
				// copy the latest image into the work buffered
				Graphics2D g2 = workImage.createGraphics();
				g2.drawImage(buffered,0,0,null);

				// visualize the current results
				if (mode == 1) {
					drawSelected(g2);
				} else if (mode == 3) {
					if( success ) {
						drawTrack(g2);
					}
				}
			}

			repaint();
		}
	}