public void compareToNaive() {
		ImageFloat32 inten = new ImageFloat32(30, 40);

		QueueCorner naiveCorners = new QueueCorner(inten.getWidth() * inten.getHeight());

		for (int useSubImage = 0; useSubImage <= 1; useSubImage++) {
			// make sure it handles sub images correctly
			if (useSubImage == 1) {
				ImageFloat32 larger = new ImageFloat32(inten.width + 10, inten.height + 8);
				inten = larger.subimage(5, 5, inten.width + 5, inten.height + 5);
			}

			for (int nonMaxWidth = 3; nonMaxWidth <= 9; nonMaxWidth += 2) {
				int radius = nonMaxWidth / 2;
				NonMaxExtractorNaive reg = new NonMaxExtractorNaive(strict);
				reg.setSearchRadius(radius);
				reg.setThreshold(0.6f);

				for (int i = 0; i < 10; i++) {
					ImageMiscOps.fillUniform(inten, rand, 0, 10);


					// detect the corners
					findLocalMaximums(inten, 0.6f, radius, 0);
					naiveCorners.reset();
					reg.process(inten, naiveCorners);

					// check the number of corners
					assertTrue(found.size() > 0);

					assertEquals(naiveCorners.size(), found.size());

					for (int j = 0; j < naiveCorners.size(); j++) {
						Point2D_I16 b = naiveCorners.get(j);

						boolean foundMatch = false;
						for (int k = 0; k < found.size(); k++) {
							Point2D_I16 a = found.get(k);

							if (a.x == b.x && a.y == b.y) {
								foundMatch = true;
								break;
							}
						}

						assertTrue(foundMatch);
					}
				}
			}
		}
	}