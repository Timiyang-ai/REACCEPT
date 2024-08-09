public final static Image scale(Image srcImage, int width, int height, Color fixedColor) {
		int srcHeight = srcImage.getHeight(null);
		int srcWidth = srcImage.getWidth(null);
		double heightRatio = NumberUtil.div(height, srcHeight);
		double widthRatio = NumberUtil.div(width, srcWidth);
		if(heightRatio == widthRatio) {
			//长宽都按照相同比例缩放时，返回缩放后的图片
			return scale(srcImage, width, height);
		}
		
		Image itemp = null;
		//宽缩放比例小就按照宽缩放，否则按照高缩放
		if(widthRatio < height) {
			itemp = scale(srcImage, width, (int)(srcHeight * widthRatio));
		}else {
			itemp = scale(srcImage, (int)(srcWidth * heightRatio), height);
		}
			
		if (null == fixedColor) {// 补白
			fixedColor = Color.WHITE;
		}
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		
		//设置背景
		g.setBackground(fixedColor);
		g.clearRect(0, 0, width, height);
		
		final int itempHeight = itemp.getHeight(null);
		final int itempWidth = itemp.getWidth(null);
		//在中间贴图
		g.drawImage(itemp, (width - itempWidth) / 2, (height - itempHeight) / 2, itempWidth, itempHeight, fixedColor, null);
		
		g.dispose();
		itemp = image;
		return itemp;
	}