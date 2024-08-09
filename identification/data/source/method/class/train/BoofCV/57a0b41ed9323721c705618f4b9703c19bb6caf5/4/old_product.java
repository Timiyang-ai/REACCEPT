public static BufferedImage convertTo( ImageBase src, BufferedImage dst, boolean orderRgb ) {
		if( src instanceof ImageSingleBand ) {
			if( ImageUInt8.class == src.getClass() ) {
				return convertTo((ImageUInt8)src,dst);
			} else if( ImageInt16.class.isInstance(src) ) {
				return convertTo((ImageInt16)src,dst);
			} else if( ImageFloat32.class == src.getClass() ) {
				return convertTo((ImageFloat32)src,dst);
			} else {
				throw new IllegalArgumentException("ImageSingleBand type is not yet supported: "+src.getClass().getSimpleName());
			}
		} else if( src instanceof MultiSpectral ) {
			MultiSpectral ms = (MultiSpectral)src;

			if( ImageUInt8.class == ms.getBandType() ) {
				return convertTo_U8((MultiSpectral<ImageUInt8>) ms, dst, orderRgb);
			} else if( ImageFloat32.class == ms.getBandType() ) {
				return convertTo_F32((MultiSpectral<ImageFloat32>) ms, dst, orderRgb);
			} else {
				throw new IllegalArgumentException("MultiSpectral type is not yet supported: "+ ms.getBandType().getSimpleName());
			}
		} else if( src instanceof ImageInterleaved ) {
			if( InterleavedU8.class == src.getClass() ) {
				return convertTo((InterleavedU8)src,dst,orderRgb);
			} else if( InterleavedF32.class == src.getClass() ) {
				return convertTo((InterleavedF32)src,dst,orderRgb);
			} else {
				throw new IllegalArgumentException("ImageSingleBand type is not yet supported: "+src.getClass().getSimpleName());
			}
		}

		throw new IllegalArgumentException("Image type is not yet supported: "+src.getClass().getSimpleName());
	}