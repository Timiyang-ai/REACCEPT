public final static void scale(File srcImageFile, File destImageFile, int scale, boolean flag) {
		try {
			BufferedImage src = ImageIO.read(srcImageFile); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {// 放大
				width = width * scale;
				height = height * scale;
			} else {// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, IMAGE_TYPE_JPEG, destImageFile);// 输出到文件流
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}