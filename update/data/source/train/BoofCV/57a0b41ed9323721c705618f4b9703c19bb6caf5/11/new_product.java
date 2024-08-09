public static <T extends ImageGray>
	GrayU8 threshold(T input , GrayU8 output ,
					 double threshold , boolean down )
	{
		if( input instanceof GrayF32) {
			return ThresholdImageOps.threshold((GrayF32)input,output,(float)threshold,down);
		} else if( input instanceof GrayU8) {
			return ThresholdImageOps.threshold((GrayU8)input,output,(int)threshold,down);
		} else if( input instanceof GrayU16) {
			return ThresholdImageOps.threshold((GrayU16)input,output,(int)threshold,down);
		} else if( input instanceof GrayS16) {
			return ThresholdImageOps.threshold((GrayS16)input,output,(int)threshold,down);
		} else if( input instanceof GrayS32) {
			return ThresholdImageOps.threshold((GrayS32)input,output,(int)threshold,down);
		} else if( input instanceof GrayF64) {
			return ThresholdImageOps.threshold((GrayF64)input,output,threshold,down);
		} else {
			throw new IllegalArgumentException("Unknown image type: "+input.getClass().getSimpleName());
		}
	}