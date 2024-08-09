public static <T extends ImageBase>
	void features(T ii, int c_x, int c_y,
				  double theta, Kernel2D_F64 weight,
				  int widthLargeGrid, int widthSubRegion, int widthSample, double scale,
				  boolean useHaar, boolean inBounds,
				  double[] features)
	{
		SparseImageGradient<T,?> gradient = createGradient(inBounds,useHaar,widthSample,scale,(Class<T>)ii.getClass());
		gradient.setImage(ii);

		int regionSize = widthLargeGrid*widthSubRegion;
		ImplSurfDescribeOps.features(c_x, c_y, theta, weight, regionSize, widthSubRegion, scale, gradient, features);
	}