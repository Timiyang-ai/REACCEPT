public static <K extends Kernel2D> SteerableKernel<K> gaussian(Class<K> kernelType, int orderX, int orderY, double sigma, int radius) {
		if( orderX < 0 || orderX > 4 )
			throw new IllegalArgumentException("derivX must be from 0 to 4 inclusive.");
		if( orderY < 0 || orderY > 4 )
			throw new IllegalArgumentException("derivT must be from 0 to 4 inclusive.");

		int order = orderX + orderY;

		if( order > 4 ) {
			throw new IllegalArgumentException("The total order of x and y can't be greater than 4");
		}
		int maxOrder = Math.max(orderX,orderY);

		if( sigma <= 0 )
			sigma = (float)FactoryKernelGaussian.sigmaForRadius(radius,maxOrder);
		else if( radius <= 0 )
			radius = FactoryKernelGaussian.radiusForSigma(sigma,maxOrder);

		Class kernel1DType = FactoryKernel.get1DType(kernelType);
		Kernel1D kerX =  FactoryKernelGaussian.derivativeK(kernel1DType,orderX,sigma,radius);
		Kernel1D kerY = FactoryKernelGaussian.derivativeK(kernel1DType,orderY,sigma,radius);
		Kernel2D kernel = GKernelMath.convolve(kerY,kerX);

		Kernel2D []basis = new Kernel2D[order+1];

		// convert it into an image which can be rotated
		ImageBase image = GKernelMath.convertToImage(kernel);
		ImageBase imageRotated = image._createNew(image.width,image.height);

		float centerX = image.width/2;
		float centerY = image.height/2;

		basis[0] = kernel;

		// form the basis by created rotated versions of the kernel
		double angleStep = Math.PI/basis.length;

		for( int index = 1; index <= order; index++ ) {
			float angle = (float)(angleStep*index);

			GeneralizedImageOps.fill(imageRotated,0);
			DistortImageOps.rotate(image,imageRotated, TypeInterpolate.BILINEAR,centerX,centerY,angle);

			basis[index] = GKernelMath.convertToKernel(imageRotated);
		}

		SteerableKernel<K> ret;

		if( kernelType == Kernel2D_F32.class )
			ret = (SteerableKernel<K>)new SteerableKernel_F32();
		else
			ret = (SteerableKernel<K>)new SteerableKernel_I32();

		ret.setBasis(FactorySteerCoefficients.polynomial(order),basis);

		return ret;
	}