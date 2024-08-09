public static Collection<String> getPackagesFromFile(File file) {
		
		// End early if we're given a non jar file
		if (!file.getName().endsWith(".jar")) {
			return Collections.<String> emptySet();
		}
		
		Set<String> packagesProvided = new HashSet<String>();
		
		JarFile jar = null;
		try {
			jar = new JarFile(file);
			
			Enumeration<JarEntry> jarEntries = jar.entries();
			while (jarEntries.hasMoreElements()) {
				JarEntry jarEntry = jarEntries.nextElement();
				if (jarEntry.isDirectory()) {
					// skip over directory entries, we only care about files
					continue;
				}
				String name = jarEntry.getName();
				
				// Skip over some folders in the jar/omod
				if (name.startsWith("lib") || name.startsWith("META-INF") || name.startsWith("web/module")) {
					continue;
				}
				
				Integer indexOfLastSlash = name.lastIndexOf("/");
				if (indexOfLastSlash <= 0) {
					continue;
				}
				String packageName = name.substring(0, indexOfLastSlash);
				
				packageName = packageName.replaceAll("/", ".");
				
				if (packagesProvided.add(packageName) && log.isTraceEnabled()) {
					log.trace("Adding module's jarentry with package: " + packageName);
				}
			}
			
			jar.close();
		}
		catch (IOException e) {
			log.error("Error while reading file: " + file.getAbsolutePath(), e);
		}
		finally {
			if (jar != null) {
				try {
					jar.close();
				}
				catch (IOException e) {
					// Ignore quietly
				}
			}
		}
		
		return packagesProvided;
	}