public T getDefaultExtension() {
	    getExtensionClasses();
        if(null == cachedDefaultName || cachedDefaultName.length() == 0) {
            return null;
        }
        return getExtension(cachedDefaultName);
	}