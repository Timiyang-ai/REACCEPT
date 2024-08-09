public final static void scale(File srcImageFile, File destImageFile, int height, int width, Color fixedColor) {
		try {
			double ratio = 0.0; // 缩放比例
			BufferedImage bi = ImageIO.read(srcImageFile);
			Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (null != fixedColor) {// 补白
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(fixedColor);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), fixedColor, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), fixedColor, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, IMAGE_TYPE_JPEG, destImageFile);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}