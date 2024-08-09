public static <T extends ImageBase>
	void scale( T input , T output , TypeInterpolate type ) {
		Class<T> inputType = (Class<T>)input.getClass();

		PixelTransform model = DistortSupport.transformScale(output, input);
		ImageDistort<T> distorter = DistortSupport.createDistort(inputType,model,type);

		distorter.apply(input,output);
	}