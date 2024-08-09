public static <T extends ImageBase>
	void rotate( T input , T output , TypeInterpolate type ,
				 float centerX , float centerY , float angle ) {
		Class<T> inputType = (Class<T>)input.getClass();

		PixelTransform model = DistortSupport.transformRotate(centerX,centerY,angle);
		ImageDistort<T> distorter = DistortSupport.createDistort(inputType,model,type);

		distorter.apply(input,output);
	}