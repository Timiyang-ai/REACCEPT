@SuppressWarnings("unchecked")
	protected void addIfMatching(Test test, String fqn) {
		try {
			String externalName = fqn.substring(0, fqn.indexOf('.')).replace('/', '.');
			ClassLoader loader = getClassLoader();
			log.trace("Checking to see if class " + externalName + " matches criteria [" + test + "]");

			Class type = loader.loadClass(externalName);
			if (test.matches(type)) {
				matches.add((Class<T>) type);
			}
		}
		catch (Throwable t) {
			log.warn("Could not examine class '" + fqn + "'" + " due to a " +
					t.getClass().getName() + " with message: " + t.getMessage());
		}
	}