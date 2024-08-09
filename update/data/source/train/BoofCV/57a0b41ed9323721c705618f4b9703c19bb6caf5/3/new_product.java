@Deprecated
	public static <T extends ImageBase>
	void scale(T input, T output, BorderType borderType, TypeInterpolate interpType) {

		PixelTransformAffine_F32 model = DistortSupport.transformScale(output, input, null);

		if( input instanceof ImageGray) {
			distortSingle((ImageGray) input, (ImageGray) output, model, interpType, borderType);
		} else if( input instanceof Planar) {
			distortMS((Planar) input, (Planar) output, model,  borderType, interpType);
		}
	}