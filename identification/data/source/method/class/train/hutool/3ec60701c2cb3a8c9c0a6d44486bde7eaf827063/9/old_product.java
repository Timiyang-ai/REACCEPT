public static String getAbsolutePath(String path){
		if(path == null) {
			path = StrUtil.EMPTY;
		}else {
			path = path.replaceAll("[/\\\\]{1,}", "/");
			
			if(path.startsWith("/") || path.matches("^[a-zA-Z]:/.*")){
				//给定的路径已经是绝对路径了
				return path;
			}
			
		}
		
		ClassLoader classLoader = ClassUtil.getClassLoader();
		URL url = classLoader.getResource(path);
		String reultPath= url != null ? url.getPath() : classLoader.getResource(StrUtil.EMPTY).getPath() + path;
		return StrUtil.removePrefix(reultPath, PATH_FILE_PRE);
	}