public static String downloadString(String url, String customCharset) {
		if(StrUtil.isBlank(url)){
			throw new NullPointerException("[url] is null!");
		}
		
		InputStream in = null;
		try {
			in = new URL(url).openStream();
			return IoUtil.read(in, customCharset);
		}catch(IOException e){
			throw new HttpException(e);
		}finally {
			IoUtil.close(in);
		}
	}