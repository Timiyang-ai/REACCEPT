public double SumOutlinkAuthorityScore(Page page) {
		List<String> outLinks = page.getOutlinks();
		double authScore = 0;
		for (String outLink1 : outLinks) {
			Page outLink = pTable.get(outLink1);
			if (outLink != null)
				authScore += outLink.authority;
		}
		return authScore;
	}