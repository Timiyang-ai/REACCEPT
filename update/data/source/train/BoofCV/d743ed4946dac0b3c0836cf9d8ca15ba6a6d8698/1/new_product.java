boolean centerOnSquare(QrCode.Alignment pattern, float guessY, float guessX) {
		float step = 1;
		float bestMag = Float.MAX_VALUE;
		float bestX = guessX;
		float bestY = guessY;

		for (int i = 0; i < 10; i++) {
			for (int row = 0; row < 3; row++) {
				float gridy = guessY - 1f + row;
				for (int col = 0; col < 3; col++) {
					float gridx = guessX - 1f + col;

					samples[row*3+col] = reader.read(gridy,gridx);
				}
			}

			float dx = (samples[2]+samples[5]+samples[8])-(samples[0]+samples[3]+samples[6]);
			float dy = (samples[6]+samples[7]+samples[8])-(samples[0]+samples[1]+samples[2]);

			float r = (float)Math.sqrt(dx*dx + dy*dy);

			if( bestMag > r ) {
//				System.out.println("good step at "+i);
				bestMag = r;
				bestX = guessX;
				bestY = guessY;
			} else {
//				System.out.println("bad step at "+i);
				step *= 0.75f;
			}

			if( r > 0 ) {
				guessX = bestX + step * dx / r;
				guessY = bestY + step * dy / r;
			} else {
				break;
			}
		}

		pattern.moduleFound.x = bestX;
		pattern.moduleFound.y = bestY;

		reader.gridToImage((float)pattern.moduleFound.y,(float)pattern.moduleFound.x,pattern.pixel);

		return true;
	}