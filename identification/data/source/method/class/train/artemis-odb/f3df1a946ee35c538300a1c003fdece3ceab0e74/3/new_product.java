public final void process() {
		if(enabled && checkProcessing()) {
			begin();
			
			if (activesIsDirty && world.isRebuildingIndexAllowed())
				rebuildCompressedActives();
			
			processEntities(actives);

			end();
		}
	}