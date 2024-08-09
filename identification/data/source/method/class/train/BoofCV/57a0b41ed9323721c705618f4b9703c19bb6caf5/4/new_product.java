public static BufferedImage convertTo( ImageBase src, BufferedImage dst, boolean orderRgb ) {
		if( src instanceof ImageGray) {
			if( GrayU8.class == src.getClass() ) {
				return convertTo((GrayU8)src,dst);
			} else if( GrayI16.class.isInstance(src) ) {
				return convertTo((GrayI16)src,dst);
			} else if( GrayF32.class == src.getClass() ) {
				return convertTo((GrayF32)src,dst);
			} else {
				throw new IllegalArgumentException("ImageSingleBand type is not yet supported: "+src.getClass().getSimpleName());
			}
		} else if( src instanceof Planar) {
			Planar ms = (Planar)src;

			if( GrayU8.class == ms.getBandType() ) {
				return convertTo_U8((Planar<GrayU8>) ms, dst, orderRgb);
			} else if( GrayF32.class == ms.getBandType() ) {
				return convertTo_F32((Planar<GrayF32>) ms, dst, orderRgb);
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