public void process( BufferedImage image ) {
		T input = ConvertBufferedImage.convertFromSingle(image, null, imageType);
		pyramid.process(input);

		ImagePyramidPanel<T> gui = new ImagePyramidPanel<T>();
		gui.set(pyramid, true);
		gui.render();

		ShowImages.showWindow(gui,"Image Pyramid Float");

		// To get an image at any of the scales simply call this get function
		T imageAtScale = pyramid.getLayer(1);

		ShowImages.showWindow(ConvertBufferedImage.convertTo(imageAtScale,null,true),"Image at layer 1");
	}