public void setOriginToCurrent() {
		IT currToWorld = (IT)worldToCurr.invert(null);
		IT oldWorldToNewWorld = (IT)worldToInit.concat(currToWorld,null);

		PixelTransform_F32 newToOld = converter.convertPixel(oldWorldToNewWorld,null);

		// fill in the background color
		GImageMiscOps.fill(workImage, 0);
		// render the transform
		distorter.setModel(newToOld);
		distorter.apply(stitchedImage, workImage);

		// swap the two images
		I s = workImage;
		workImage = stitchedImage;
		stitchedImage = s;

		// have motion estimates be relative to this frame
		motion.setToFirst();
		worldToCurr.reset();
		first = true;
	}