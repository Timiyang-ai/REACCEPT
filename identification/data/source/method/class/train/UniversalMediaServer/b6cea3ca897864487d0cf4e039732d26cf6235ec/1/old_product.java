public static String getFileNameWithRewriting(String f, File file) {
		String fileNameWithoutExtension;
		formattedName = "";
		String formattedNameTemp;
		String searchFormattedName;
		edition = "";
		boolean loopedOnce = false;

		// These are false unless we recognize that we could use some info on the video from IMDB
		boolean isEpisodeToLookup = false;
		boolean isMovieToLookup   = false;

		// Remove file extension
		fileNameWithoutExtension = getFileNameWithoutExtension(f);
		formattedName = fileNameWithoutExtension;
		searchFormattedName = "";

		if (formattedName.matches(".*[sS]0\\d[eE]\\d\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes within the first 9 seasons that are double or triple episodes

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3-$1$4$5");
			formattedName = formattedName.replaceAll("[\\s\\.]S0(\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3-$1$4$5");
			removeAndSaveEditionToBeAddedLater();

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3-$1$4$5 - ");
			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			formattedName = formattedNameTemp;
			removeStuffAtTheEndOfFilename();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter(loopedOnce);
		} else if (formattedName.matches(".*[sS][1-9]\\d[eE]\\d\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes after their first 9 seasons that are double episodes

			// Rename the season/episode numbers. For example, "S11E01" changes to " - 1101"
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3-$1$4$5");
			formattedName = formattedName.replaceAll("[\\s\\.]S([1-9]\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3-$1$4$5");
			removeAndSaveEditionToBeAddedLater();

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3-$1$4$5 - ");
			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			formattedName = formattedNameTemp;
			removeStuffAtTheEndOfFilename();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter(loopedOnce);
		} else if (formattedName.matches(".*[sS]0\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes within the first 9 seasons
			removeAndSaveEditionToBeAddedLater();

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3");
			formattedName = formattedName.replaceAll("[\\s\\.]S0(\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3");

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3 - ");
			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			formattedName = formattedNameTemp;
			removeStuffAtTheEndOfFilename();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter(loopedOnce);
		} else if (formattedName.matches(".*[sS][1-9]\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes after their first 9 seasons

			// Rename the season/episode numbers. For example, "S11E01" changes to " - 1101"
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3");
			formattedName = formattedName.replaceAll("[\\s\\.]S([1-9]\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3");
			removeAndSaveEditionToBeAddedLater();

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3 - ");
			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			formattedName = formattedNameTemp;
			removeStuffAtTheEndOfFilename();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter(loopedOnce);
		} else if (formattedName.matches(".*[\\s\\.](19|20)\\d\\d[\\s\\.][0-1]\\d[\\s\\.][0-3]\\d[\\s\\.].*")) {
			// This matches scene and most p2p TV episodes that release several times per week

			// Rename the date. For example, "2013.03.18" changes to " - 2013/03/18"
			formattedName = formattedName.replaceAll("(?i)[\\s\\.](19|20)(\\d\\d)[\\s\\.]([0-1]\\d)[\\s\\.]([0-3]\\d)(" + commonFileEnds + ")", " - $1$2/$3/$4");
			formattedName = formattedName.replaceAll("[\\s\\.](19|20)(\\d\\d)[\\s\\.]([0-1]\\d)[\\s\\.]([0-3]\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2/$3/$4");
			removeAndSaveEditionToBeAddedLater();

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.](19|20)(\\d\\d)[\\s\\.]([0-1]\\d)[\\s\\.]([0-3]\\d)[\\s\\.]", " - $1$2/$3/$4 - ");
			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			formattedName = formattedNameTemp;
			removeStuffAtTheEndOfFilename();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter(loopedOnce);
		} else if (formattedName.matches(".*[\\s\\.](19|20)\\d\\d[\\s\\.].*")) {
			// This matches scene and most p2p movies

			// Rename the year. For example, "2013" changes to " (2013)"
			formattedName = formattedName.replaceAll("[\\s\\.](19|20)(\\d\\d)", " ($1$2)");
			removeStuffAtTheEndOfFilename();
			removeAndSaveEditionToBeAddedLater();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter();
		} else if (formattedName.matches(".*\\[(19|20)\\d\\d\\].*")) {
			// This matches rarer types of movies

			// Rename the year. For example, "2013" changes to " (2013)"
			formattedName = formattedName.replaceAll("(?i)\\[(19|20)(\\d\\d)\\].*", " ($1$2)");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter();
		} else if (formattedName.matches(".*\\((19|20)\\d\\d\\).*")) {
			// This matches rarer types of movies
			removeStuffAtTheEndOfFilename();
			capitalizeFirstLetter();
		} else if (formattedName.matches(commonFileEndsMatch)) {
			// This is probably a movie that doesn't specify a year
			isMovieToLookup = true;
			removeStuffAtTheEndOfFilename();
			removeAndSaveEditionToBeAddedLater();

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			capitalizeFirstLetter();
		} else if (formattedName.matches(".*\\[[0-9a-zA-Z]{8}\\]$")) {
			// This matches anime with a hash at the end of the name

			// Remove underscores
			formattedName = formattedName.replaceAll("_", " ");

			// Remove stuff at the end of the filename like hash, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)\\s\\(1280x720.*|\\s\\(1920x1080.*|\\s\\(720x400.*|\\[720p.*|\\[1080p.*|\\[480p.*|\\s\\(BD.*|\\s\\[Blu-Ray.*|\\s\\[DVD.*|\\.DVD.*|\\[[0-9a-zA-Z]{8}\\]$|\\[h264.*|R1DVD.*|\\[BD.*", "");
			removeGroupNameFromBeginnig(fileNameWithoutExtension);

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.substring(formattedName.length() - 3).matches("[\\s\\._]\\d\\d")) {
				isEpisodeToLookup = true;
				searchFormattedName = formattedName.substring(0, formattedName.length() - 2) + "S01E" + formattedName.substring(formattedName.length() - 2);
			}

			capitalizeFirstLetter();
		} else if (formattedName.matches(".*\\[BD\\].*|.*\\[720p\\].*|.*\\[1080p\\].*|.*\\[480p\\].*|.*\\[Blu-Ray.*|.*\\[h264.*")) {
			// This matches anime without a hash in the name

			// Remove underscores
			formattedName = formattedName.replaceAll("_", " ");

			// Remove stuff at the end of the filename like hash, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)\\[BD\\].*|\\[720p.*|\\[1080p.*|\\[480p.*|\\[Blu-Ray.*\\[h264.*", "");
			removeGroupNameFromBeginnig(fileNameWithoutExtension);

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.substring(formattedName.length() - 3).matches("[\\s\\._]\\d\\d")) {
				isEpisodeToLookup = true;
				searchFormattedName = formattedName.substring(0, formattedName.length() - 2) + "S01E" + formattedName.substring(formattedName.length() - 2);
			}

			capitalizeFirstLetter();
		}

		// Remove extra spaces
		formattedName = formattedName.replaceAll("  ", " ");

		// Add episode name (if not there)
		if (file != null && (isEpisodeToLookup || isMovieToLookup)) {
			InfoDb.InfoDbData info = PMS.get().infoDb().get(file);
			if (info == null) {
				PMS.get().infoDbAdd(file, searchFormattedName);
			} else if (isEpisodeToLookup && StringUtils.isNotEmpty(info.ep_name)) {
				formattedName += " - " + info.ep_name;
			} else if (isMovieToLookup && StringUtils.isNotEmpty(info.year)) {
				formattedName += " (" + info.year + ")";
			}
		}

		// Add the edition information if it exists
		if (!edition.isEmpty()) {
			formattedName = formattedName.trim();
			String substr = formattedName.substring(Math.max(0, formattedName.length() - 2));
			if (" -".equals(substr)) {
				formattedName = formattedName.substring(0, formattedName.length() - 2);
			}
			formattedName += " " + edition;
		}

		return formattedName;
	}