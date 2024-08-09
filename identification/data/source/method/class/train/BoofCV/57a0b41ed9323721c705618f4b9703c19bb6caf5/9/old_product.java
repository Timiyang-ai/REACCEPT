@Deprecated
	public static <T extends ImageBase>
	void scale(T input, T output, BorderType borderType, TypeInterpolate interpType) {

		PixelTransformAffine_F32 model = DistortSupport.transformScale(output, input, null);

		if( input instanceof ImageSingleBand ) {
			distortSingle((ImageSingleBand) input, (ImageSingleBand) output, model, interpType, borderType);
		} else if( input instanceof MultiSpectral ) {
			distortMS((MultiSpectral) input, (MultiSpectral) output, model,  borderType, interpType);
		}
	}