protected void markMerge(int regionA, int regionB) {

		int dA = mergeList.data[regionA];
		int dB = mergeList.data[regionB];

		// Quick check to see if they reference the same node
		if( dA == dB ) {
			return;
		}

		// search down to the root node  (set-find)
		int rootA = regionA;
		while( dA != rootA ) {
			rootA = dA;
			dA = mergeList.data[rootA];
		}

		int rootB = regionB;
		while( dB != rootB ) {
			rootB = dB;
			dB = mergeList.data[rootB];
		}

		// make rootA the parent.  This allows the quick test to pass in the future
		mergeList.data[regionA] = rootA;
		mergeList.data[regionB] = rootA;
		mergeList.data[rootB] = rootA;
	}