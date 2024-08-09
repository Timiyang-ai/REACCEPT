public static <T extends ImageBase>
	void scale( T input , T output , TypeInterpolate interpType ) {
		Class<T> inputType = (Class<T>)input.getClass();
		InterpolatePixel<T> interp = FactoryInterpolation.createPixel(0, 255, interpType, inputType);

		scale(input,output,interp);
	}