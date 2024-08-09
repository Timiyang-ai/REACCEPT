public static String[] getFileNameMetadata(String filename) {
		if (filename == null) {
			return new String[] { null, null, null, null, null, null };
		}

		String formattedName;

		// These are false unless we recognize that we could use some info on the video from IMDb
		boolean isMovieWithoutYear = false;
		boolean isSample = false;

		String movieOrShowName = null;
		String year            = null;
		String tvSeason        = null;
		String tvEpisodeName   = null;
		String tvEpisodeNumber = null;
		String edition         = null;
		
		// This can contain editions and "Sample" for now
		String extraInformation = null;

		Pattern pattern;
		Matcher matcher;

		formattedName = basicPrettify(filename);

		if (formattedName.toLowerCase(Locale.ENGLISH).endsWith("sample")) {
			isSample = true;
		}

		if (formattedName.matches(".*[sS]\\d\\d[eE]\\d\\d([eE]|-[eE])\\d\\d.*")) {
			// This matches scene and most p2p TV episodes within the first 9 seasons that are more than one episode
			pattern = Pattern.compile("[sS](\\d\\d)[eE](\\d\\d)(?:[eE]|-[eE])(\\d\\d)");
			matcher = pattern.matcher(formattedName);

			if (matcher.find()) {
				tvSeason = matcher.group(1);
				tvEpisodeNumber = matcher.group(2);
				tvEpisodeNumber += "-" + matcher.group(3);
			}

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS_CASE_SENSITIVE + ")", "");
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS + ")", "");
			formattedName = formattedName.replaceAll("(?i)\\sS(\\d\\d)E(\\d)(\\d)([eE]|-[eE])(\\d)(\\d)\\s", " - $1$2$3-$5$6 - ");
			formattedName = formattedName.replaceAll("(?i)\\sS(\\d\\d)E(\\d)(\\d)([eE]|-[eE])(\\d)(\\d)", " - $1$2$3-$5$6");
			formattedName = formattedName.replaceAll("\\sS(\\d\\d)E(\\d)(\\d)([eE]|-[eE])(\\d)(\\d)", " - $1$2$3-$5$6");
			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			formattedName = removeFilenameEndMetadata(formattedName);

			formattedName = convertFormattedNameToTitleCaseParts(formattedName);
		} else if (formattedName.matches(".*[sS]\\d\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes within the first 9 seasons
			pattern = Pattern.compile("[sS](\\d\\d)[eE](\\d\\d)");
			matcher = pattern.matcher(formattedName);
			if (matcher.find()) {
				tvSeason = matcher.group(1);
				tvEpisodeNumber = matcher.group(2);
			}

			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS_CASE_SENSITIVE + ")", "");
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS + ")", "");
			formattedName = formattedName.replaceAll("(?i)\\sS(\\d\\d)E(\\d)(\\d)\\s", " - $1$2$3 - ");
			formattedName = formattedName.replaceAll("(?i)\\sS(\\d\\d)E(\\d)(\\d)", " - $1$2$3");
			formattedName = formattedName.replaceAll("\\sS(\\d\\d)E(\\d)(\\d)", " - $1$2$3");
			formattedName = removeFilenameEndMetadata(formattedName);
			formattedName = convertFormattedNameToTitleCaseParts(formattedName);
		} else if (formattedName.matches(".*\\s\\d[xX]\\d\\d.*")) {
			// This matches older scene and most p2p TV episodes within the first 9 seasons
			pattern = Pattern.compile("\\s(\\d)[xX](\\d\\d)");
			matcher = pattern.matcher(formattedName);
			if (matcher.find()) {
				tvSeason = "0" + matcher.group(1);
				tvEpisodeNumber = matcher.group(2);
			}

			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS_CASE_SENSITIVE + ")", "");
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS + ")", "");
			formattedName = formattedName.replaceAll("(?i)\\s(\\d)x(\\d)(\\d)\\s", " - $1$2$3 - ");
			formattedName = formattedName.replaceAll("(?i)\\s(\\d)x(\\d)(\\d)", " - $1$2$3");
			formattedName = formattedName.replaceAll("\\s(\\d)x(\\d)(\\d)", " - $1$2$3");
			formattedName = removeFilenameEndMetadata(formattedName);
			formattedName = convertFormattedNameToTitleCaseParts(formattedName);
		} else if (formattedName.matches(".*\\s\\d\\d[xX]\\d\\d.*")) {
			// This matches older scene and most p2p TV episodes after the first 9 seasons
			pattern = Pattern.compile("\\s(\\d\\d)[xX](\\d\\d)");
			matcher = pattern.matcher(formattedName);
			if (matcher.find()) {
				tvSeason = matcher.group(1);
				tvEpisodeNumber = matcher.group(2);
			}

			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			// Rename the season/episode numbers. For example, "12x01" changes to " - 1201"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS_CASE_SENSITIVE + ")", "");
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS + ")", "");
			formattedName = formattedName.replaceAll("(?i)\\s(\\d\\d)x(\\d)(\\d)\\s", " - $1$2$3 - ");
			formattedName = formattedName.replaceAll("(?i)\\s(\\d\\d)x(\\d)(\\d)", " - $1$2$3");
			formattedName = formattedName.replaceAll("\\s(\\d\\d)x(\\d)(\\d)", " - $1$2$3");
			formattedName = removeFilenameEndMetadata(formattedName);
			formattedName = convertFormattedNameToTitleCaseParts(formattedName);
		} else if (formattedName.matches(".*\\s(19|20)\\d\\d\\s[0-1]\\d\\s[0-3]\\d\\s.*")) {
			// This matches scene and most p2p TV episodes that release several times per week
			pattern = Pattern.compile("\\s((?:19|20)\\d\\d)\\s([0-1]\\d)\\s([0-3]\\d)\\s");
			matcher = pattern.matcher(formattedName);
			if (matcher.find()) {
				tvSeason = matcher.group(1);
				tvEpisodeNumber = matcher.group(2);
				tvEpisodeNumber += "/" + matcher.group(3);
			}

			// Rename the date. For example, "2013.03.18" changes to " - 2013/03/18"
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS_CASE_SENSITIVE + ")", "");
			formattedName = formattedName.replaceAll("(" + COMMON_FILE_ENDS + ")", "");
			formattedName = formattedName.replaceAll("(?i)\\s(19|20)(\\d\\d)\\s([0-1]\\d)\\s([0-3]\\d)\\s", " - $1$2/$3/$4 - ");
			formattedName = formattedName.replaceAll("(?i)\\s(19|20)(\\d\\d)\\s([0-1]\\d)\\s([0-3]\\d)", " - $1$2/$3/$4");
			formattedName = formattedName.replaceAll("\\s(19|20)(\\d\\d)\\s([0-1]\\d)\\s([0-3]\\d)", " - $1$2/$3/$4");
			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			formattedName = removeFilenameEndMetadata(formattedName);
			formattedName = convertFormattedNameToTitleCaseParts(formattedName);
		} else if (formattedName.matches(".*\\s(19|20)\\d\\d\\s.*")) {
			// This matches scene and most p2p movies

			// Rename the year. For example, "2013" changes to " (2013)"
			formattedName = formattedName.replaceAll("\\s(19|20)(\\d\\d)", " ($1$2)");
			formattedName = removeFilenameEndMetadata(formattedName);
			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			formattedName = convertFormattedNameToTitleCase(formattedName);
		} else if (formattedName.matches(".*\\[(19|20)\\d\\d\\].*")) {
			// This matches rarer types of movies

			// Rename the year. For example, "2013" changes to " (2013)"
			formattedName = formattedName.replaceAll("(?i)\\[(19|20)(\\d\\d)\\].*", " ($1$2)");
			formattedName = removeFilenameEndMetadata(formattedName);

			formattedName = convertFormattedNameToTitleCase(formattedName);
		} else if (formattedName.matches(".*\\((19|20)\\d\\d\\).*")) {
			// This matches rarer types of movies
			formattedName = removeFilenameEndMetadata(formattedName);

			formattedName = convertFormattedNameToTitleCase(formattedName);
		} else if (formattedName.matches(".*\\[[0-9a-zA-Z]{8}\\]$")) {
			// This matches a single episode anime with a hash at the end of the name
			matcher = COMMON_ANIME_EPISODE_NUMBERS_PATTERN.matcher(formattedName);
			if (matcher.find()) {
				tvSeason = "1";
				tvEpisodeNumber = matcher.group(1);

				int showNameIndex = indexOf(COMMON_ANIME_EPISODE_NUMBERS_PATTERN, formattedName);
				if (showNameIndex != -1) {
					movieOrShowName = formattedName.substring(0, showNameIndex);
				}
			} else {
				// This matches a multiple episode anime with a hash at the end of the name
				matcher = COMMON_ANIME_MULTIPLE_EPISODES_NUMBERS_PATTERN.matcher(formattedName);
				if (matcher.find()) {
					tvSeason = "1";
					tvEpisodeNumber = matcher.group(1);

					int showNameIndex = indexOf(COMMON_ANIME_MULTIPLE_EPISODES_NUMBERS_PATTERN, formattedName);
					if (showNameIndex != -1) {
						movieOrShowName = formattedName.substring(0, showNameIndex);
					}
				}
			}

			// Remove stuff at the end of the filename like hash, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)\\s\\(1280x720.*|\\s\\(1920x1080.*|\\s\\(720x400.*|\\[720p.*|\\[1080p.*|\\[480p.*|\\s\\(BD.*|\\s\\[Blu-Ray.*|\\s\\[DVD.*|\\.DVD.*|\\[[0-9a-zA-Z]{8}\\]$|\\[h264.*|R1DVD.*|\\[BD.*", "");

			formattedName = convertFormattedNameToTitleCase(formattedName);
		} else if (formattedName.matches(".*\\[BD\\].*|.*\\[720p\\].*|.*\\[1080p\\].*|.*\\[480p\\].*|.*\\[Blu-Ray.*|.*\\[h264.*")) {
			// This matches anime without a hash in the name
			matcher = COMMON_ANIME_EPISODE_NUMBERS_PATTERN.matcher(formattedName);
			if (matcher.find()) {
				tvSeason = "1";
				tvEpisodeNumber = matcher.group(1);

				int showNameIndex = indexOf(COMMON_ANIME_EPISODE_NUMBERS_PATTERN, formattedName);
				if (showNameIndex != -1) {
					movieOrShowName = formattedName.substring(0, showNameIndex);
				}
			}

			// Remove stuff at the end of the filename like hash, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)\\[BD\\].*|\\[720p.*|\\[1080p.*|\\[480p.*|\\[Blu-Ray.*|\\[h264.*", "");

			formattedName = convertFormattedNameToTitleCase(formattedName);
		} else if (formattedName.matches(COMMON_FILE_ENDS_MATCH)) {
			// This is probably a movie that doesn't specify a year
			isMovieWithoutYear = true;
			formattedName = removeFilenameEndMetadata(formattedName);
			FormattedNameAndEdition result = removeAndSaveEditionToBeAddedLater(formattedName);
			formattedName = result.formattedName;
			if (result.edition != null) {
				edition = result.edition;
			}

			formattedName = convertFormattedNameToTitleCase(formattedName);
		}

		// Remove extra spaces
		formattedName = formattedName.replaceAll("\\s+", " ");
		formattedName = formattedName.trim();
		if (movieOrShowName != null) {
			movieOrShowName = movieOrShowName.trim();
			String substr = movieOrShowName.substring(Math.max(0, movieOrShowName.length() - 2));
			if (" -".equals(substr)) {
				movieOrShowName = movieOrShowName.substring(0, movieOrShowName.length() - 2);
			}
		}

		if (tvSeason != null) {
			// Remove leading 0 from the season if it exists
			tvSeason = StringUtils.stripStart(tvSeason, "0");

			pattern = Pattern.compile("(?i) - (\\d{2}|\\d{4}|\\d{4}/\\d{2}/\\d{2}) - (.*)");
			int showNameIndex = indexOf(pattern, formattedName);
			if (StringUtils.isEmpty(movieOrShowName)) {
				if (showNameIndex != -1) {
					movieOrShowName = formattedName.substring(0, showNameIndex);

					matcher = pattern.matcher(formattedName);
					if (matcher.find()) {
						tvEpisodeName = matcher.group(2).trim();
						if (StringUtils.isEmpty(tvEpisodeName)) {
							tvEpisodeName = null;
						}
					}
				} else {
					showNameIndex = indexOf(Pattern.compile("(?i) - (\\d{2}|\\d{4}|\\d{4}/\\d{2}/\\d{2})"), formattedName);
					if (showNameIndex != -1) {
						movieOrShowName = formattedName.substring(0, showNameIndex);
					}
				}
			}
		} else {
			if (isMovieWithoutYear) {
				movieOrShowName = formattedName;
			} else {
				int yearIndex = indexOf(Pattern.compile("\\s\\((?:19|20)\\d{2}\\)"), formattedName);
				if (yearIndex > -1) {
					movieOrShowName = formattedName.substring(0, yearIndex);
					year = formattedName.substring(yearIndex + 2, yearIndex + 6);
				}
			}
		}

		// Retain the fact it is a sample clip
		if (isSample) {
			if (edition == null) {
				extraInformation = "";
			} else {
				extraInformation = edition + " ";
			}
			extraInformation += "(Sample)";
		} else {
			extraInformation = edition;
		}

		return new String[] { movieOrShowName, year, extraInformation, tvSeason, tvEpisodeNumber, tvEpisodeName };
	}