public static int[] transToStartEnd(int pageNo, int countPerPage) {
		if(pageNo <1) {
			pageNo = 1;
		}
		
		if(countPerPage < 1) {
			countPerPage = 0;
			StaticLog.warn("Count per page  [{}] is not valid!", countPerPage);
		}
		
		int start = (pageNo -1) * countPerPage;
		int end = start + countPerPage;
		
		return new int[]{start, end};
	}