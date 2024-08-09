public static boolean del(File file) throws IORuntimeException {
		if (file == null || file.exists() == false) {
			return true;
		}

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File childFile : files) {
				boolean isOk = del(childFile);
				if (isOk == false) {
					// 删除一个出错则本次删除任务失败
					return false;
				}
			}
		}
		return file.delete();
	}