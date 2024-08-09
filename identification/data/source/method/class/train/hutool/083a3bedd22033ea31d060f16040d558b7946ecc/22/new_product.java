public static Image scale(Image srcImg, float scale) {
		if (scale < 0) {
			// 自动修正负数
			scale = -scale;
		}

		int width = NumberUtil.mul(Integer.toString(srcImg.getWidth(null)), Float.toString(scale)).intValue(); // 得到源图宽
		int height = NumberUtil.mul(Integer.toString(srcImg.getHeight(null)), Float.toString(scale)).intValue(); // 得到源图长
		return scale(srcImg, width, height);
	}