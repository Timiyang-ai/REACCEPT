public synchronized void dispose () {
		threadPool.shutdown();
		Array<String> assets = assetTypes.keys().toArray();
		for(String asset: assets) {
			remove(asset);
		}
	}