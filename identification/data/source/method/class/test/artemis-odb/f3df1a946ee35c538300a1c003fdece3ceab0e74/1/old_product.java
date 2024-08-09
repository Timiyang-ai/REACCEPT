public final void process() {
		if(enabled && checkProcessing()) {
			begin();
			
			if (activesIsDirty)
				rebuildCompressedActives();
			
			isProcessing = true;
			processEntities(actives);
			isProcessing = false;

			int s = delayedDeletion.size();
			if (s > 0) {
				Object[] data = delayedDeletion.getData();
				for (int i = 0; i < s; i++) {
					removeFromSystem((Entity)data[i]);
					data[i] = null;
				}
				delayedDeletion.setSize(0);
			}
			
			end();
		}
	}