void findErrorLocatorBM( int syndromes[] , int length , GrowQueue_I8 errorLocator ) {
		GrowQueue_I8 C = errorLocator; // error polynomial
		GrowQueue_I8 B = new GrowQueue_I8();  // previous error polynomial

		initToOne(C,length+1);
		initToOne(B,length+1);

		GrowQueue_I8 tmp = new GrowQueue_I8(length);

//		int L = 0;
//		int m = 1; // stores how much B is 'shifted' by
		int b = 1;

		for (int n = 0; n < length; n++) {

			// Compute discrepancy delta
			int delta = syndromes[n];

			for (int j = 1; j < C.size; j++) {
				delta ^= math.multiply(C.data[C.size-j-1]&0xFF, syndromes[n-j]);
			}

			// B = D^m * B
			B.data[B.size++] = 0;

			// Step 3 is implicitly handled
			// m = m + 1

			if( delta != 0 ) {
				int scale = math.multiply(delta, math.inverse(b));
				math.polyAddScaleB(C, B, scale, tmp);

				if (2 * C.size > length) {
					// if 2*L > N ---- Step 4
//					m += 1;
				} else {
					// if 2*L <= N --- Step 5
					B.setTo(C);
//					L = n+1-L;
					b = delta;
//					m = 1;
				}
				C.setTo(tmp);
			}
		}

		// TODO drop leading zeros?
		System.out.println();
	}