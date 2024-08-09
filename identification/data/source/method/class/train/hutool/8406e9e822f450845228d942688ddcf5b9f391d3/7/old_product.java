public static File copy(File src, File dest, boolean isOverride) throws IORuntimeException {
		// check
		Assert.notNull(src, "Source File is null !");
		if (false == src.exists()) {
			throw new IORuntimeException("File not exist: " + src);
		}
		Assert.notNull(dest, "Destination File or directiory is null !");
		if (equals(src, dest)) {
			throw new IORuntimeException("Files '" + src + "' and '" + dest + "' are equal");
		}

		if (src.isDirectory()) {// 复制目录
			internalCopyDir(src, dest, isOverride);
		} else {// 复制文件
			internalCopyFile(src, dest, isOverride);
		}
		return dest;
	}