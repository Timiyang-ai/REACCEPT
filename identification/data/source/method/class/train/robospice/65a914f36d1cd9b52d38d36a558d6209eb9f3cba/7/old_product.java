@Override
	public final String loadDataFromNetwork() throws Exception {
		try {
			Log.d(getClass().getName(), "Call web service " + url);
			return CharStreams.toString(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
		} catch (MalformedURLException e) {
			Log.e(getClass().getName(), "Unable to create image URL", e);
			return null;
		} catch (IOException e) {
			Log.e(getClass().getName(), "Unable to download image", e);
			return null;
		}
	}