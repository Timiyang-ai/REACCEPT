public final static void scale(Image srcImage, ImageOutputStream destImageStream, int height, int width, Color fixedColor) {
		try {
			double ratio = 0.0; // 缩放比例
			BufferedImage bi = toBufferedImage(srcImage);
			Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = ((double) height) / bi.getHeight();
				} else {
					ratio = ((double) width) / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (null != fixedColor) {// 补白
				final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(fixedColor);
				g.fillRect(0, 0, width, height);

				final int itempHeight = itemp.getHeight(null);
				final int itempWidth = itemp.getWidth(null);
				if (width == itempWidth) {
					// 宽度一致
					g.drawImage(itemp, 0, (height - itempHeight) / 2, itempWidth, itempHeight, fixedColor, null);
				} else {
					g.drawImage(itemp, (width - itempWidth) / 2, 0, itempWidth, itempHeight, fixedColor, null);
				}
				g.dispose();
				itemp = image;
			}
			ImageIO.write(toBufferedImage(itemp), IMAGE_TYPE_JPEG, destImageStream);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}