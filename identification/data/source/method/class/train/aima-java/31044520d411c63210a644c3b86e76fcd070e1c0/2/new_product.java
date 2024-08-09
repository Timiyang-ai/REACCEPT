public double SumInlinkHubScore(Page page) {
		List<String> inLinks = page.getInlinks();
		double hubScore = 0;
		for (String inLink1 : inLinks) {
			Page inLink = pTable.get(inLink1);
			if (inLink != null)
				hubScore += inLink.hub;
			// else: page is linked to by a Page not in our table
		}
		return hubScore;
	}