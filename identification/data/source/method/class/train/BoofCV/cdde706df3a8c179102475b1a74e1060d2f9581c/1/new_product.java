public static <T extends ImageBase>
	void features(T ii, double c_x, double c_y,
				  double theta, Kernel2D_F64 weight,
				  int widthLargeGrid, int widthSubRegion, int widthSample, double scale,
				  boolean useHaar, boolean inBounds,
				  double[] features)
	{
		SparseImageGradient<T,?> gradient = createGradient(inBounds,useHaar,widthSample,scale,(Class<T>)ii.getClass());
		gradient.setImage(ii);

		ImplSurfDescribeOps.features(c_x, c_y, theta, weight, widthLargeGrid, widthSubRegion, scale, gradient, features);
	}