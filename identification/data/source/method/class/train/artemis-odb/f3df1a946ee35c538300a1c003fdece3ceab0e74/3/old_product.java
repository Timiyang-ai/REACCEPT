public final void process() {
		if(enabled && checkProcessing()) {
			world.updateEntityStates();
			
			begin();
			
			if (activesIsDirty && world.isRebuildingIndexAllowed())
				rebuildCompressedActives();
			
			processEntities(actives);

			end();
		}
	}