public static final String idFromName(String name) {
        return name.replaceAll("[^\\w-\\.]", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }