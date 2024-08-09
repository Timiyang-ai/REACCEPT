public static Image scale(Image srcImg, double scale) {
		if (scale < 0) {
			// 自动修正负数
			scale = -scale;
		}

		int width = NumberUtil.mul(Integer.toString(srcImg.getWidth(null)), Double.toString(scale)).intValue(); // 得到源图宽
		int height = NumberUtil.mul(Integer.toString(srcImg.getHeight(null)), Double.toString(scale)).intValue(); // 得到源图长
		return scale(srcImg, width, height);
	}