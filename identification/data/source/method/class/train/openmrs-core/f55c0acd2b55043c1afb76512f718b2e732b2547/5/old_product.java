public static Collection<String> getPackagesFromFile(File file) {
		
		// end early if we're given a non jar file
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
					// skip over directory entries, we only care about dirs with files in it
					continue;
				}
				String name = jarEntry.getName();
				Integer indexOfLastSlash = name.lastIndexOf("/");
				if (indexOfLastSlash <= 0) {
					continue;
				}
				String packageName = name.substring(0, indexOfLastSlash);
				
				// skip over some folders in the jar/omod
				if ("lib".equals(packageName) || "META-INF".equals(packageName) || packageName.startsWith("web/module")) {
					continue;
				}
				
				packageName = packageName.replaceAll("/", ".");
				
				if (packagesProvided.add(packageName)) {
					log.trace("Adding module's jarentry with package: " + packageName);
				}
			}
			
		}
		catch (IOException e) {
			log.error("Unable to open jar from file: " + file.getAbsolutePath(), e);
		}
		finally {
			if (jar != null) {
				try {
					jar.close();
				}
				catch (IOException e) {
					// do nothing
				}
			}
			
		}
		
		// clean up packages contained within other packages this is
		// O(n^2), but its better than putting extra packages into the
		// set and having the classloader continually loop over them
		Set<String> packagesProvidedCopy = new HashSet<String>();
		packagesProvidedCopy.addAll(packagesProvided);
		
		for (String packageNameOuter : packagesProvidedCopy) {
			// add the period so that we don't match to ourselves or to
			// similarly named packages. eg. org.pih and org.pihrwanda
			// should not match
			packageNameOuter += ".";
			for (String packageNameInner : packagesProvidedCopy) {
				if (packageNameInner.contains(packageNameOuter)) {
					packagesProvided.remove(packageNameInner);
				}
			}
			
		}
		// end cleanup
		
		return packagesProvided;
	}