@Test
	public void testGetFileNameMetadata() throws Exception {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger logger = context.getLogger(Logger.ROOT_LOGGER_NAME);
		JsonParser parser = new JsonParser();

		try {
			JsonElement tree = parser.parse(
				new java.io.FileReader(
					FileUtils.toFile(
						CLASS.getResource("prettified_filenames_metadata.json")
					)
				)
			);

			JsonArray tests = tree.getAsJsonArray();
			for (JsonElement test : tests) {
				JsonObject o = test.getAsJsonObject();
				String original = o.get("filename").getAsString();
				JsonObject metadata = o.get("metadata").getAsJsonObject();
				boolean todo = false;
				if (o.has("todo")) {
					todo = o.get("todo").getAsBoolean();
				}

				String[] extracted_metadata = FileUtil.getFileNameMetadata(original);
				assert extracted_metadata.length == 6;
				String movieOrShowName = extracted_metadata[0];
				int year = -1;
				try {
					if (extracted_metadata[1] != null) {
						year = Integer.parseInt(extracted_metadata[1]);
					}
				} catch (NumberFormatException ex) {
					throw (new AssertionError(ex));
				}
				String extraInformation = extracted_metadata[2];
				int tvSeason = -1;
				try {
					if (extracted_metadata[3] != null) {
						tvSeason = Integer.parseInt(extracted_metadata[3]);
					}
				} catch (NumberFormatException ex) {
					throw (new AssertionError(ex));
				}
				// tvEpisodeNumber might be a single episode, but might also be
				// a hyphen-separated range, so cannot always parse as int
				String tvEpisodeNumber = extracted_metadata[4];
				String tvEpisodeName = extracted_metadata[5];

				JsonElement elem;

				elem = metadata.get("extra");
				if (elem != null) {
					for (JsonElement extra : elem.getAsJsonArray()) {
						try {
							assertThat(extraInformation.indexOf(extra.getAsString()) > -1).isTrue();
						} catch (NullPointerException ex) {
							// There is no extraInformation extracted
							if (todo) {
								logger.warn("testGetFileNameMetadata/extra would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							// extraInformation is extracted, but is wrong
							if (todo) {
								logger.warn("testGetFileNameMetadata/extra would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
				}
				if ("tv-series-episode".equals(metadata.get("type").getAsString())) {
					logger.debug("Doing tv-series-episode " + original);
					// A single episode, might have episode title and date
					elem = metadata.get("series");
					if (elem != null) {
						try {
							assertThat(movieOrShowName).isEqualTo(elem.getAsString());
						} catch (NullPointerException ex) {
							// There is no movieOrShowName extracted
							if (todo) {
								logger.warn("testGetFileNameMetadata/series would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							// movieOrShowName is extracted, but is wrong
							if (todo) {
								logger.warn("testGetFileNameMetadata/series would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
					elem = metadata.get("season");
					if (elem != null) {
						try {
							assertThat(tvSeason).isEqualTo(elem.getAsInt());
						} catch (NullPointerException ex) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/season would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/season would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
					elem = metadata.get("episode");
					if (elem != null) {
						try {
							assertThat(tvEpisodeNumber).isEqualTo(String.format("%02d", elem.getAsInt()));
						} catch (NumberFormatException | NullPointerException ex) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/episode would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/episode would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
					if (metadata.has("released")) {
						JsonObject metadata_rel = metadata.get("released").getAsJsonObject();
						String rel_date = Integer.toString(metadata_rel.get("year").getAsInt());
						if (metadata_rel.has("month")) {
							rel_date = rel_date + "-" + Integer.toString(metadata_rel.get("month").getAsInt());
						}
						if (metadata_rel.has("date")) {
							rel_date = rel_date + "-" + Integer.toString(metadata_rel.get("date").getAsInt());
						}
					}
					elem = metadata.get("title");
					if (elem != null) {
						try {
							assertThat(tvEpisodeName).isEqualTo(elem.getAsString());
						} catch (NullPointerException ex) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/title would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/title would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
				} else if ("tv-series-episodes".equals(metadata.get("type").getAsString())) {
					logger.debug("Doing tv-series-episodes " + original);
					// A single episode or an episode range, cannot have episode title or date
					elem = metadata.get("series");
					if (elem != null) {
						try {
							assertThat(movieOrShowName).isEqualTo(elem.getAsString());
						} catch (NullPointerException ex) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/series would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/series would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
					elem = metadata.get("season");
					if (elem != null) {
						assertThat(tvSeason).isEqualTo(elem.getAsInt());
					}
					elem = metadata.get("episodes");
					if (elem != null) {
						String range = "";
						for (JsonElement elem2 : elem.getAsJsonArray()) {
							range = range + "-" + String.format("%02d", elem2.getAsInt());
						}
						try {
							assertThat(tvEpisodeNumber).isEqualTo(range.substring(1));
						} catch (AssertionError err) {
							if (todo) {
								logger.warn("testGetFileNameMetadata/episodes would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
				} else if ("movie".equals(metadata.get("type").getAsString())) {
					logger.debug("Doing movie " + original);
					elem = metadata.get("title");
					if (elem != null) {
						try {
							assertThat(movieOrShowName).isEqualTo(elem.getAsString());
						} catch (NullPointerException ex) {
							// There is no movieOrShowName extracted
							if (todo) {
								logger.warn("testGetFileNameMetadata/title would fail for TODO test " + original);
							} else {
								throw (new AssertionError(ex));
							}
						} catch (AssertionError err) {
							// movieOrShowName is extracted, but is wrong
							if (todo) {
								logger.warn("testGetFileNameMetadata/title would fail for TODO test " + original);
							} else {
								throw (err);
							}
						}
					}
					if (metadata.has("released")) {
						JsonObject metadata_rel = metadata.get("released").getAsJsonObject();
						elem = metadata_rel.get("year");
						if (elem != null) {
							try {
								assertThat(year).isEqualTo(elem.getAsInt());
							} catch (AssertionError err) {
								if (todo) {
									logger.warn("testGetFileNameMetadata/released would fail for TODO test " + original);
								} else {
									throw (err);
								}
							}
						}
					}
				} else {
					logger.error("Unknown content type in " + original);
				}
			} // for all test cases
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			throw (new AssertionError(ex));
		}
	}