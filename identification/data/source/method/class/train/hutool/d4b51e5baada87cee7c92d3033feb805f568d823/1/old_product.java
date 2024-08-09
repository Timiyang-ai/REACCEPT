public static int totalPage(int totalCount, int numPerPage) {
		if(numPerPage == 0) {
			return 0;
		}
		return totalCount % numPerPage == 0 ? totalCount / numPerPage : totalCount / numPerPage + 1;
	}