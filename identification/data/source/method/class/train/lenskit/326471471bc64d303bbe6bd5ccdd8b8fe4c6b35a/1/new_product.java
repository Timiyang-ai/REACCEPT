@Deprecated
    private static MutableSparseVector userRatingVector(ArrayList<Rating> ratings) {
    	Rating rp = null;
    	for (Rating r: ratings) {
    		if (rp != null && ITEM_TIME_COMPARATOR.compare(rp, r) > 0) {
    			Collections.sort(ratings, ITEM_TIME_COMPARATOR);
    			break;
    		}
    		rp = r;
    	}
    	
    	// collect the list of unique item IDs
    	long[] items = new long[ratings.size()];
    	double[] values = new double[ratings.size()];
    	int li = -1;
    	for (Rating r: ratings) {
    		long iid = r.getItemId();
    		if (li < 0 || items[li] != iid)
    			li++;
    		items[li] = iid;
    		values[li] = r.getRating();
    	}
    	
    	return MutableSparseVector.wrap(items, values, li+1);
    }