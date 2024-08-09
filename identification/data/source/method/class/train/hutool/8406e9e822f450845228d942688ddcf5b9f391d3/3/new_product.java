public static File copy(File src, File dest, boolean isOverride) throws IOException {
		// check
		if (!src.exists()) {
			throw new FileNotFoundException("File not exist: " + src);
		}
		if (equals(src, dest)) {
			throw new IOException("Files '" + src + "' and '" + dest + "' are equal");
		}

		//复制目录
		if (src.isDirectory()) {
			if(dest.isFile()){
				throw new IOException(StrUtil.format("Src [{}] is a directory but Dest [{}] is a file!", src.getPath(), dest.getPath()));
			}
			
			if (!dest.exists()) {
				dest.mkdirs();
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// 递归复制
				copy(srcFile, destFile, isOverride);
			}
			return dest;
		}

		//检查目标
		if (dest.exists()) {
			if (dest.isDirectory()) {
				dest = new File(dest, src.getName());
			}
			if (false == isOverride) {
				//不覆盖，直接跳过
				StaticLog.debug("File [{}] already exist", dest);
				return dest;
			}
		}else{
			touch(dest);
		}

		// do copy file
		FileInputStream input = new FileInputStream(src);
		FileOutputStream output = new FileOutputStream(dest);
		try {
			IoUtil.copy(input, output);
		} finally {
			IoUtil.close(output);
			IoUtil.close(input);
		}

		if (src.length() != dest.length()) {
			throw new IOException("Copy file failed of '" + src + "' to '" + dest + "' due to different sizes");
		}
		
		return dest;
	}