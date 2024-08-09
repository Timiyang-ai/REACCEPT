public static <T extends ImageBase>
	void rotate( T input , T output , TypeInterpolate interpType , float angleInputToOutput ) {

		Class<T> inputType = (Class<T>)input.getClass();
		InterpolatePixel<T> interp = FactoryInterpolation.createPixel(0, 255, interpType, inputType);

		rotate(input, output, interp, angleInputToOutput);
	}