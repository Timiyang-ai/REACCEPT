protected void checkMerge( int regionA , int regionB ) {

		int dA = mergeList.data[regionA];
		int dB = mergeList.data[regionB];

		// see if they link to the same thing doing the quick check
		if( dA != -1 && dB != -1 ) {
			if( dA == dB )
				return;
		} else if( dA != -1 ) {
			if( dA == regionB )
				return;
		} else if( dB != -1 ) {
			if( dB == regionA )
				return;
		}

		// search down to the root node
		int rootA = regionA;
		while( dA != -1 ) {
			rootA = dA;
			dA = mergeList.data[rootA];
		}

		int rootB = regionB;
		while( dB != -1 ) {
			rootB = dB;
			dB = mergeList.data[rootB];
		}

		// if they are not the same link merge one into the other
		if( rootA != rootB ) {
			mergeList.data[rootB] = rootA;
		}

		// make it so that the quick check will work the next time        '
		if( regionB != rootA ) {
			mergeList.data[regionB] = rootA;
		}
		if( mergeList.data[regionA] != -1 ) {
			mergeList.data[regionA] = rootA;
		}
	}