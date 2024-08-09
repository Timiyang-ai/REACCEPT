@Override
	public final void process() {
		if(enabled && checkProcessing()) {
			begin();
			processEntities(actives);
			end();
		}
	}