public static List<String> listFileNames(String path) {
		if (path == null) {
			return null;
		}
		path = getAbsolutePath(path);
		if (path.endsWith(String.valueOf(UNIX_SEPARATOR)) == false) {
			path = path + UNIX_SEPARATOR;
		}

		List<String> paths = new ArrayList<String>();
		int index = path.lastIndexOf(FileUtil.JAR_PATH_EXT);
		try {
			if (index == -1) {
				// 普通目录路径
				File[] files = ls(path);
				for (File file : files) {
					if (file.isFile()) {
						paths.add(file.getName());
					}
				}
			} else {
				// jar文件中的路径
				index = index + FileUtil.JAR_FILE_EXT.length();
				final String jarPath = path.substring(0, index);
				final String subPath = path.substring(index + 2);
				for (JarEntry entry : Collections.list(new JarFile(jarPath).entries())) {
					final String name = entry.getName();
					if (name.startsWith(subPath)) {
						String nameSuffix = StrUtil.removePrefix(name, subPath);
						if (nameSuffix.contains(String.valueOf(UNIX_SEPARATOR)) == false) {
							paths.add(nameSuffix);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new UtilException(StrUtil.format("Can not read file path of [{}]", path), e);
		}
		return paths;
	}