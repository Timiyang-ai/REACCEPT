@Override
	public MultiSpectral<T> subimage(int x0, int y0, int x1, int y1, MultiSpectral<T> subimage) {
		if (x0 < 0 || y0 < 0)
			throw new IllegalArgumentException("x0 or y0 is less than zero");
		if (x1 < x0 || y1 < y0)
			throw new IllegalArgumentException("x1 or y1 is less than x0 or y0 respectively");
		if (x1 > width || y1 > height)
			throw new IllegalArgumentException("x1 or y1 is more than the width or height respectively");

		MultiSpectral<T> ret = new MultiSpectral<T>(type,bands.length);
		ret.stride = Math.max(width, stride);
		ret.width = x1 - x0;
		ret.height = y1 - y0;
		ret.startIndex = startIndex + y0 * stride + x0;
		ret.subImage = true;

		for( int i = 0; i < bands.length; i++ ) {
			ret.bands[i] = (T)bands[i].subimage(x0,y0,x1,y1);
		}
		
		return ret;
	}