public static void main (String[] args) {
		File tmxFile, baseDir, outputDir;

		Settings settings = new Settings();
		settings.padding = 2;
		settings.duplicatePadding = true;

		// Create a new JoglApplication so that Gdx stuff works properly
		new JoglApplication(new ApplicationListener() {
			@Override public void create () {
			}

			@Override public void dispose () {
			}

			@Override public void pause () {
			}

			@Override public void render () {
			}

			@Override public void resize (int width, int height) {
			}

			@Override public void resume () {
			}
		}, "", 0, 0, false);

		TiledMapPacker packer = new TiledMapPacker();

		if (args.length != 3) {
			System.out.println("Usage: TMXFILE BASEDIR OUTPUTDIR");
			return;
		}

		tmxFile = new File(args[0]);
		baseDir = new File(args[1]);
		outputDir = new File(args[2]);

		if (!baseDir.exists()) {
			throw new RuntimeException("Base directory does not exist");
		}
		if (!tmxFile.exists()) {
			throw new RuntimeException("TMX file does not exist");
		}

		try {
			packer.processMap(tmxFile, baseDir, outputDir, settings);
		} catch (IOException e) {
			throw new RuntimeException("Error processing map: " + e.getMessage());
		}

		System.exit(0);
	}