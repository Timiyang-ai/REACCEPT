public synchronized <T> void load (String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
		AssetLoader loader = getLoader(type, fileName);
		if (loader == null) throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(type));

		if (loadQueue.size == 0) {
			loaded = 0;
			toLoad = 0;
		}

		// check if an asset with the same name but a different type has already been added.

		// check preload queue
		for (int i = 0; i < loadQueue.size; i++) {
			AssetDescriptor desc = loadQueue.get(i);
			if (desc.fileName.equals(fileName) && !desc.type.equals(type))
				throw new GdxRuntimeException("Asset with name '" + fileName
					+ "' already in preload queue, but has different type (expected: " + ClassReflection.getSimpleName(type) + ", found: "
					+ ClassReflection.getSimpleName(desc.type) + ")");
		}

		// check task list
		for (int i = 0; i < tasks.size(); i++) {
			AssetDescriptor desc = tasks.get(i).assetDesc;
			if (desc.fileName.equals(fileName) && !desc.type.equals(type))
				throw new GdxRuntimeException("Asset with name '" + fileName
					+ "' already in task list, but has different type (expected: " + ClassReflection.getSimpleName(type) + ", found: "
					+ ClassReflection.getSimpleName(desc.type) + ")");
		}

		// check loaded assets
		Class otherType = assetTypes.get(fileName);
		if (otherType != null && !otherType.equals(type))
			throw new GdxRuntimeException("Asset with name '" + fileName + "' already loaded, but has different type (expected: "
				+ ClassReflection.getSimpleName(type) + ", found: " + ClassReflection.getSimpleName(otherType) + ")");

		toLoad++;
		AssetDescriptor assetDesc = new AssetDescriptor(fileName, type, parameter);
		loadQueue.add(assetDesc);
		log.debug("Queued: " + assetDesc);
	}