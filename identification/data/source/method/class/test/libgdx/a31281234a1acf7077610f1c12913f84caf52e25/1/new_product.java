public static void main (String[] args) {
		File tmxFile, inputDir, outputDir;

		Settings settings = new Settings();

		// Note: the settings below are now default...
		settings.padding = 2;
		settings.duplicatePadding = true;

		// Create a new JoglApplication so that Gdx stuff works properly
		TiledMapPacker packer = new TiledMapPacker();

		if (args.length != 2) {
			System.out.println("Usage: INPUTDIR OUTPUTDIR");
			System.exit(0);
		}

		inputDir = new File(args[0]);
		outputDir = new File(args[1]);

		if (!inputDir.exists()) {
			throw new RuntimeException("Input directory does not exist");
		}

		try {
			packer.processMap(inputDir, outputDir, settings);
		} catch (IOException e) {
			throw new RuntimeException("Error processing map: " + e.getMessage());
		}

		System.exit(0);
	}