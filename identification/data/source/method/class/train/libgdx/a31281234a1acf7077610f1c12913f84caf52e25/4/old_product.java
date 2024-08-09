public static void main (String[] args) {
		final Settings texturePackerSettings = new Settings();
		texturePackerSettings.paddingX = 2;
		texturePackerSettings.paddingY = 2;
		texturePackerSettings.duplicatePadding = true;

		final TiledMapPackerSettings packerSettings = new TiledMapPackerSettings();

		switch (args.length) {
		case 3: {
			inputDir = new File(args[0]);
			outputDir = new File(args[1]);
			if ("--strip-unused".equals(args[2])) {
				packerSettings.stripUnusedTiles = true;
			}
			break;
		}
		case 2: {
			inputDir = new File(args[0]);
			outputDir = new File(args[1]);
			break;
		}
		case 1: {
			inputDir = new File(args[0]);
			outputDir = new File(inputDir, "output/");
			break;
		}
		default: {
			System.out.println("Usage: INPUTDIR [OUTPUTDIR] [--strip-unused]");
			System.exit(0);
		}
		}

		new LwjglApplication(new ApplicationListener() {

			@Override
			public void resume () {
			}

			@Override
			public void resize (int width, int height) {
			}

			@Override
			public void render () {
			}

			@Override
			public void pause () {
			}

			@Override
			public void dispose () {
			}

			@Override
			public void create () {
				TiledMapPacker packer = new TiledMapPacker(packerSettings);

				if (!inputDir.exists()) {
					throw new RuntimeException("Input directory does not exist");
				}

				try {
					packer.processMaps(inputDir, outputDir, texturePackerSettings);
				} catch (IOException e) {
					throw new RuntimeException("Error processing map: " + e.getMessage());
				}

				Gdx.app.exit();
			}
		}, "TiledMapPacker", 100, 50, true);
	}