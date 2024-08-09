@Test
	public void gaussian() {

		for( int totalOrder = 1; totalOrder <= 4; totalOrder++ ) {
			for( int orderX = 0; orderX<= totalOrder; orderX++ ) {
				int orderY = totalOrder-orderX;

				SteerableKernel<Kernel2D_F32> alg = FactorySteerable.gaussian(Kernel2D_F32.class,orderX,orderY,10);

				Kernel2D_F32 k = alg.compute(0.1);

				// make sure its not zero.
				boolean notZero = false;
				for( int y = 0; y < k.width; y++ ) {
					for( int x = 0; x < k.width; x++ ) {
						if( k.get(x,y) != 0 )
							notZero=true;
					}
				}
				assertTrue(notZero);
			}
		}
	}