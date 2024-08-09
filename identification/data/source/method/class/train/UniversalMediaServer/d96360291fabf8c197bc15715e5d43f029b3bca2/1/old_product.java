public static String getFileNameWithRewriting(String f, File file) {
		String fileNameWithoutExtension;
		String formattedName;
		String formattedNameTemp;
		String searchFormattedName;
		boolean loopedOnce = false;

		// These are false unless we recognize that we could use some info on the video from IMDB
		boolean isEpisodeToLookup = false;
		boolean isMovieToLookup   = false;

		// Remove file extension
		fileNameWithoutExtension = getFileNameWithoutExtension(f);
		formattedName = fileNameWithoutExtension;
		searchFormattedName = "";

		String commonFileEnds = "[\\s\\.]AC3.*|[\\s\\.]REPACK.*|[\\s\\.]480p.*|[\\s\\.]720p.*|[\\s\\.]m-720p.*|[\\s\\.]900p.*|[\\s\\.]1080p.*|[\\s\\.]WEB-DL.*|[\\s\\.]HDTV.*|[\\s\\.]DSR.*|[\\s\\.]PDTV.*|[\\s\\.]WS.*|[\\s\\.]HQ.*|[\\s\\.]DVDRip.*|[\\s\\.]TVRiP.*|[\\s\\.]BDRip.*|[\\s\\.]BRRip.*|[\\s\\.]WEBRip.*|[\\s\\.]BluRay.*|[\\s\\.]Blu-ray.*|[\\s\\.]SUBBED.*|[\\s\\.]x264.*|[\\s\\.]Dual[\\s\\.]Audio.*|[\\s\\.]HSBS.*|[\\s\\.]H-SBS.*|[\\s\\.]RERiP.*|[\\s\\.]DIRFIX.*|[\\s\\.]READNFO.*|[\\s\\.]60FPS.*";
		String commonFileEndsMatch = ".*[\\s\\.]AC3.*|.*[\\s\\.]REPACK.*|.*[\\s\\.]480p.*|.*[\\s\\.]720p.*|.*[\\s\\.]m-720p.*|.*[\\s\\.]900p.*|.*[\\s\\.]1080p.*|.*[\\s\\.]WEB-DL.*|.*[\\s\\.]HDTV.*|.*[\\s\\.]DSR.*|.*[\\s\\.]PDTV.*|.*[\\s\\.]WS.*|.*[\\s\\.]HQ.*|.*[\\s\\.]DVDRip.*|.*[\\s\\.]TVRiP.*|.*[\\s\\.]BDRip.*|.*[\\s\\.]BRRip.*|.*[\\s\\.]WEBRip.*|.*[\\s\\.]BluRay.*|.*[\\s\\.]Blu-ray.*|.*[\\s\\.]SUBBED.*|.*[\\s\\.]x264.*|.*[\\s\\.]Dual[\\s\\.]Audio.*|.*[\\s\\.]HSBS.*|.*[\\s\\.]H-SBS.*|.*[\\s\\.]RERiP.*|.*[\\s\\.]DIRFIX.*|.*[\\s\\.]READNFO.*|.*[\\s\\.]60FPS.*";
		String commonFileEndsCaseSensitive = "[\\s\\.]PROPER[\\s\\.].*|[\\s\\.]iNTERNAL[\\s\\.].*|[\\s\\.]LIMITED[\\s\\.].*|[\\s\\.]LiMiTED[\\s\\.].*|[\\s\\.]FESTiVAL[\\s\\.].*|[\\s\\.]NORDIC[\\s\\.].*|[\\s\\.]REAL[\\s\\.].*|[\\s\\.]SUBBED[\\s\\.].*|[\\s\\.]RETAIL[\\s\\.].*";

		String commonFileMiddle = "(?i)(Special[\\s\\.]Edition|Unrated|Final[\\s\\.]Cut|Remastered|Extended[\\s\\.]Cut|Extended|IMAX[\\s\\.]Edition)";

		if (formattedName.matches(".*[sS]0\\d[eE]\\d\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes within the first 9 seasons that are double or triple episodes

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3-$1$4$5");
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3-$1$4$5");

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3-$1$4$5 - ");

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedNameTemp.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedNameTemp = "";
				for (String part : formattedName.split(" - ")) {
					if (loopedOnce) {
						formattedNameTemp += " - " + convertLowerCaseStringToTitleCase(part);
					} else {
						formattedNameTemp += convertLowerCaseStringToTitleCase(part);
					}
					loopedOnce = true;
				}
				formattedName = formattedNameTemp;
			}
		} else if (formattedName.matches(".*[sS][1-9]\\d[eE]\\d\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes after their first 9 seasons that are double episodes

			// Rename the season/episode numbers. For example, "S11E01" changes to " - 1101"
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3-$1$4$5");
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3-$1$4$5");

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3-$1$4$5 - ");

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedNameTemp.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedNameTemp = "";
				for (String part : formattedName.split(" - ")) {
					if (loopedOnce) {
						formattedNameTemp += " - " + convertLowerCaseStringToTitleCase(part);
					} else {
						formattedNameTemp += convertLowerCaseStringToTitleCase(part);
					}
					loopedOnce = true;
				}
				formattedName = formattedNameTemp;
			}
		} else if (formattedName.matches(".*[sS]0\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes within the first 9 seasons

			// Rename the season/episode numbers. For example, "S01E01" changes to " - 101"
			// Then strip the end of the episode if it does not have the episode name in the title
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3");
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3");

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S0(\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3 - ");

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedNameTemp.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedNameTemp = "";
				for (String part : formattedName.split(" - ")) {
					if (loopedOnce) {
						formattedNameTemp += " - " + convertLowerCaseStringToTitleCase(part);
					} else {
						formattedNameTemp += convertLowerCaseStringToTitleCase(part);
					}
					loopedOnce = true;
				}
				formattedName = formattedNameTemp;
			}
		} else if (formattedName.matches(".*[sS][1-9]\\d[eE]\\d\\d.*")) {
			// This matches scene and most p2p TV episodes after their first 9 seasons

			// Rename the season/episode numbers. For example, "S11E01" changes to " - 1101"
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)(" + commonFileEnds + ")", " - $1$2$3");
			formattedName = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2$3");

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.]S([1-9]\\d)E(\\d)(\\d)[\\s\\.]", " - $1$2$3 - ");

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedNameTemp.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedNameTemp = "";
				for (String part : formattedName.split(" - ")) {
					if (loopedOnce) {
						formattedNameTemp += " - " + convertLowerCaseStringToTitleCase(part);
					} else {
						formattedNameTemp += convertLowerCaseStringToTitleCase(part);
					}
					loopedOnce = true;
				}
				formattedName = formattedNameTemp;
			}
		} else if (formattedName.matches(".*[\\s\\.](19|20)\\d\\d[\\s\\.][0-1]\\d[\\s\\.][0-3]\\d[\\s\\.].*")) {
			// This matches scene and most p2p TV episodes that release several times per week

			// Rename the date. For example, "2013.03.18" changes to " - 2013/03/18"
			formattedName = formattedName.replaceAll("(?i)[\\s\\.](19|20)(\\d\\d)[\\s\\.]([0-1]\\d)[\\s\\.]([0-3]\\d)(" + commonFileEnds + ")", " - $1$2/$3/$4");
			formattedName = formattedName.replaceAll("(?i)[\\s\\.](19|20)(\\d\\d)[\\s\\.]([0-1]\\d)[\\s\\.]([0-3]\\d)(" + commonFileEndsCaseSensitive + ")", " - $1$2/$3/$4");

			// If it matches this then it didn't match the previous one, which means there is probably an episode title in the filename
			formattedNameTemp = formattedName.replaceAll("(?i)[\\s\\.](19|20)(\\d\\d)[\\s\\.]([0-1]\\d)[\\s\\.]([0-3]\\d)[\\s\\.]", " - $1$2/$3/$4 - ");

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.equals(formattedNameTemp)) {
				isEpisodeToLookup = true;
			}

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedNameTemp.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedNameTemp = "";
				for (String part : formattedName.split(" - ")) {
					if (loopedOnce) {
						formattedNameTemp += " - " + convertLowerCaseStringToTitleCase(part);
					} else {
						formattedNameTemp += convertLowerCaseStringToTitleCase(part);
					}
					loopedOnce = true;
				}
				formattedName = formattedNameTemp;
			}
		} else if (formattedName.matches(".*[\\s\\.](19|20)\\d\\d[\\s\\.].*")) {
			// This matches scene and most p2p movies

			// Rename the year. For example, "2013" changes to " (2013)"
			formattedName = formattedName.replaceAll("[\\s\\.](19|20)(\\d\\d)", " ($1$2)");

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");
			formattedName = formattedName.replaceAll("(?i)" + commonFileEnds, "");

			formattedName = formattedName.replaceAll(commonFileMiddle, "($1)");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		} else if (formattedName.matches(".*\\[(19|20)\\d\\d\\].*")) {
			// This matches rarer types of movies

			// Rename the year. For example, "2013" changes to " (2013)"
			formattedName = formattedName.replaceAll("(?i)\\[(19|20)(\\d\\d)\\].*", " ($1$2)");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		} else if (formattedName.matches(".*\\((19|20)\\d\\d\\).*")) {
			// This matches rarer types of movies

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		} else if (formattedName.matches(".*\\((19|20)\\d\\d\\).*")) {
			// This matches rarer types of movies

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		} else if (formattedName.matches(commonFileEndsMatch)) {
			// This is probably a movie that doesn't specify a year
			isMovieToLookup = true;

			// Remove stuff at the end of the filename like release group, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)" + commonFileEnds, "");
			formattedName = formattedName.replaceAll(commonFileEndsCaseSensitive, "");
			formattedName = formattedName.replaceAll(commonFileMiddle, "($1)");

			// Replace periods with spaces
			formattedName = formattedName.replaceAll("\\.", " ");

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		} else if (formattedName.matches(".*\\[[0-9a-zA-Z]{8}\\]$")) {
			// This matches anime with a hash at the end of the name

			// Remove underscores
			formattedName = formattedName.replaceAll("_", " ");

			// Remove stuff at the end of the filename like hash, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)\\s\\(1280x720.*|\\s\\(1920x1080.*|\\s\\(720x400.*|\\[720p.*|\\[1080p.*|\\[480p.*|\\s\\(BD.*|\\s\\[Blu-Ray.*|\\s\\[DVD.*|\\.DVD.*|\\[[0-9a-zA-Z]{8}\\]$|\\[h264.*|R1DVD.*|\\[BD.*", "");

			// Remove group name from the beginning of the filename
			if (!"".equals(formattedName)) {
				if (formattedName.substring(0, 1).matches("\\[")) {
					int closingBracketIndex = formattedName.indexOf(']');
					if (closingBracketIndex != -1) {
						formattedName = formattedName.substring(closingBracketIndex + 1);
					}

					if (formattedName.substring(0, 1).matches("\\s")) {
						formattedName = formattedName.substring(1);
					}
				}
			} else {
				formattedName = fileNameWithoutExtension;
			}

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.substring(formattedName.length() - 3).matches("[\\s\\._]\\d\\d")) {
				isEpisodeToLookup = true;
				searchFormattedName = formattedName.substring(0, formattedName.length() - 2) + "S01E" + formattedName.substring(formattedName.length() - 2);
			}

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		} else if (formattedName.matches(".*\\[BD\\].*|.*\\[720p\\].*|.*\\[1080p\\].*|.*\\[480p\\].*|.*\\[Blu-Ray.*|.*\\[h264.*")) {
			// This matches anime without a hash in the name

			// Remove underscores
			formattedName = formattedName.replaceAll("_", " ");

			// Remove stuff at the end of the filename like hash, quality, source, etc.
			formattedName = formattedName.replaceAll("(?i)\\[BD\\].*|\\[720p.*|\\[1080p.*|\\[480p.*|\\[Blu-Ray.*\\[h264.*", "");

			// Remove group name from the beginning of the filename
			if (!"".equals(formattedName)) {
				if (formattedName.substring(0, 1).matches("\\[")) {
					int closingBracketIndex = formattedName.indexOf(']');
					if (closingBracketIndex != -1) {
						formattedName = formattedName.substring(closingBracketIndex + 1);
					}

					if (formattedName.substring(0, 1).matches("\\s")) {
						formattedName = formattedName.substring(1);
					}
				}
			} else {
				formattedName = fileNameWithoutExtension;
			}

			if (PMS.getConfiguration().isUseInfoFromIMDB() && formattedName.substring(formattedName.length() - 3).matches("[\\s\\._]\\d\\d")) {
				isEpisodeToLookup = true;
				searchFormattedName = formattedName.substring(0, formattedName.length() - 2) + "S01E" + formattedName.substring(formattedName.length() - 2);
			}

			// Capitalize the first letter of each word if the string contains no capital letters
			if (formattedName.equals(formattedName.toLowerCase())) {
				formattedName = convertLowerCaseStringToTitleCase(formattedName);
			}
		}

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

		return formattedName;
	}