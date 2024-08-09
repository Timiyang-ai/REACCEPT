public static List<String> listFileNames(String path) throws IORuntimeException {
		if (path == null) {
			return null;
		}
		path = getAbsolutePath(path);
		if (false == path.endsWith(String.valueOf(UNIX_SEPARATOR))) {
			path = path + UNIX_SEPARATOR;
		}

		List<String> paths = new ArrayList<String>();
		int index = path.lastIndexOf(FileUtil.JAR_PATH_EXT);
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
			JarFile jarFile = null;
			try {
				jarFile = new JarFile(path.substring(0, index));
				final String subPath = path.substring(index + 2);
				for (JarEntry entry : Collections.list(jarFile.entries())) {
					final String name = entry.getName();
					if (name.startsWith(subPath)) {
						final String nameSuffix = StrUtil.removePrefix(name, subPath);
						if (nameSuffix.contains(String.valueOf(UNIX_SEPARATOR)) == false) {
							paths.add(nameSuffix);
						}
					}
				}
			} catch (IOException e) {
				throw new IORuntimeException(StrUtil.format("Can not read file path of [{}]", path), e);
			} finally {
				IoUtil.close(jarFile);
			}
		}
		return paths;
	}