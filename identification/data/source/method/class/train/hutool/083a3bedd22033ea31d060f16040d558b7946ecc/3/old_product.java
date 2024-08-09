public final static void scale(Image srcImg, ImageOutputStream destImageStream, int scale, boolean flag) {
		try {
			BufferedImage src = toBufferedImage(srcImg);
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
			ImageIO.write(toBufferedImage(image), IMAGE_TYPE_JPEG, destImageStream);// 输出到文件流
		} catch (IOException e) {
			throw new UtilException(e);
		}
	}