public double SumInlinkHubScore(Page page) {
		List<String> inLinks = page.getInlinks();
		double hubScore = 0;
		for (int i = 0; i < inLinks.size(); i++) {
			Page inLink = pTable.get(inLinks.get(i));
			if (inLink != null) {
				hubScore += inLink.hub;
			} else {
				// page is linked to by a Page not in our table
				continue;
			}
		}
		return hubScore;
	}