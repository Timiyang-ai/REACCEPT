public static void hessian( ImageFloat32 integral, int skip , int size ,
								ImageFloat32 intensity)
	{
		// todo check size with skip
//		InputSanityCheck.checkSameShape(integral,intensity);

		ImplIntegralImageFeatureIntensity.hessianBorder(integral,skip,size,intensity);
		ImplIntegralImageFeatureIntensity.hessianInner(integral,skip,size,intensity);
	}