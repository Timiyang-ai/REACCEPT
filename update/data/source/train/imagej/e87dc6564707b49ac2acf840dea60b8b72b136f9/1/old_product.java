public void setDatasetOverlays(final Dataset ds, final ImagePlus imp) {
		final OverlayManager overlayManager = ImageJ.get(OverlayManager.class);
		ShapeRoi oldROI = createROI(overlayManager.getOverlays(ds));
		if (oldROI != null) {
			float [] oldPath = oldROI.getShapeAsArray();
			Roi newROI = imp.getRoi();
			if (newROI instanceof ShapeRoi) {
				float [] newPath = ((ShapeRoi)newROI).getShapeAsArray();
				if (oldPath.length == newPath.length) {
					boolean same = true;
					for (int i = 0; i<oldPath.length; i++) {
						if (oldPath[i] != newPath[i]) {
							same = false;
							break;
						}
					}
					if (same) return;
				}
			}
		}
		final List<Overlay> overlaysToRemove = overlayManager.getOverlays(ds);
		for (Overlay overlay:overlaysToRemove) {
			overlayManager.removeOverlay(ds, overlay);
		}
		final List<Overlay> overlays = getOverlays(imp);
		overlayManager.setOverlays(ds, overlays);
	}