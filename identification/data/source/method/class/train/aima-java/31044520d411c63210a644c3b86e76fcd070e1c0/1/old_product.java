public double SumOutlinkAuthorityScore(Page page) {
		List<String> outLinks = page.getOutlinks();
		double authScore = 0;
		for (int i = 0; i < outLinks.size(); i++) {
			Page outLink = pTable.get(outLinks.get(i));
			if (outLink != null) {
				authScore += outLink.authority;
			}
		}
		return authScore;
	}