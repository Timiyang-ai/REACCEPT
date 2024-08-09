public static boolean exists(String userName, String projectName) {
		int findRowCount = find.where().eq("name", projectName)
				.eq("owner", userName).findRowCount();
		return (findRowCount != 0) ? true : false;
	}