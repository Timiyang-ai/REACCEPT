public static List<String> listFileNames(String path) throws IORuntimeException {
		if (path == null) {
			return new ArrayList<>(0);
		}
		int index = path.lastIndexOf(FileUtil.JAR_PATH_EXT);
		if (index < 0) {
			// 普通目录
			final List<String> paths = new ArrayList<>();
			final File[] files = ls(path);
			for (File file : files) {
				if (file.isFile()) {
					paths.add(file.getName());
				}
			}
			return paths;
		}

		// jar文件
		path = getAbsolutePath(path);
		// jar文件中的路径
		index = index + FileUtil.JAR_FILE_EXT.length();
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(path.substring(0, index));
			return ZipUtil.listFileNames(jarFile, path.substring(index + 1));
		} catch (IOException e) {
			throw new IORuntimeException(StrUtil.format("Can not read file path of [{}]", path), e);
		} finally {
			IoUtil.close(jarFile);
		}
	}