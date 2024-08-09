void correctErrors( GrowQueue_I8 message ,
						int length_msg_ecc,
						GrowQueue_I8 syndromes,
						GrowQueue_I8 errorLocator ,
						GrowQueue_I32 errorLocations)
	{
		GrowQueue_I8 err_eval = new GrowQueue_I8(); // TODO avoid new
		findErrorEvaluator(syndromes,errorLocator,err_eval);

		// Compute error positions
		GrowQueue_I8 X = GrowQueue_I8.zeros(errorLocations.size); // TODO avoid new
		for (int i = 0; i < errorLocations.size; i++) {
			int coef_pos = (length_msg_ecc-errorLocations.data[i]-1);
			X.data[i] = (byte)math.power(2,coef_pos);
			// The commented out code below replicates exactly how the reference code works. This code above
			// seems to work just as well and passes all the unit tests
//			int coef_pos = math.max_value-(length_msg_ecc-errorLocations.data[i]-1);
//			X.data[i] = (byte)math.power_n(2,-coef_pos);
		}

		GrowQueue_I8 err_loc_prime_tmp = new GrowQueue_I8(X.size);

		// storage for error magnitude polynomial
		for (int i = 0; i < X.size; i++) {
			int Xi = X.data[i]&0xFF;
			int Xi_inv = math.inverse(Xi);

			// Compute the polynomial derivative
			err_loc_prime_tmp.size = 0;
			for (int j = 0; j < X.size; j++) {
				if( i == j )
					continue;
				err_loc_prime_tmp.data[err_loc_prime_tmp.size++] =
						(byte)GaliosFieldOps.subtract(1,math.multiply(Xi_inv,X.data[j]&0xFF));
			}
			// compute the product, which is the denominator of Forney algorithm (errata locator derivative)
			int err_loc_prime = 1;
			for (int j = 0; j < err_loc_prime_tmp.size; j++) {
				err_loc_prime = math.multiply(err_loc_prime,err_loc_prime_tmp.data[j]&0xFF);
			}

			int y = math.polyEval_S(err_eval,Xi_inv);
			y = math.multiply(math.power(Xi,1),y);

			// Compute the magnitude
			int magnitude = math.divide(y,err_loc_prime);

			// only apply a correction if it's part of the message and not the ECC
			int loc = errorLocations.get(i);
			if( loc < message.size )
				message.data[loc] = (byte)((message.data[loc]&0xFF) ^ magnitude);
		}
	}