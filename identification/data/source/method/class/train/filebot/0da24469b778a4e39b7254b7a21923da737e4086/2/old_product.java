public synchronized List<AnidbSearchResult> getAnimeTitles() throws Exception {
		URL url = new URL("http", host, "/api/anime-titles.dat.gz");
		ResultCache cache = getCache();

		@SuppressWarnings("unchecked")
		List<AnidbSearchResult> anime = (List) cache.getSearchResult(null, Locale.ROOT);
		if (anime != null) {
			return anime;
		}

		// <aid>|<type>|<language>|<title>
		// type: 1=primary title (one per anime), 2=synonyms (multiple per anime), 3=shorttitles (multiple per anime), 4=official title (one per language)
		Pattern pattern = Pattern.compile("^(?!#)(\\d+)[|](\\d)[|]([\\w-]+)[|](.+)$");

		List<String> languageOrder = new ArrayList<String>();
		languageOrder.add("x-jat");
		languageOrder.add("en");
		languageOrder.add("ja");

		// fetch data
		Map<Integer, List<Object[]>> entriesByAnime = new HashMap<Integer, List<Object[]>>(65536);

		Scanner scanner = new Scanner(new GZIPInputStream(url.openStream()), "UTF-8");
		try {
			while (scanner.hasNextLine()) {
				Matcher matcher = pattern.matcher(scanner.nextLine());

				if (matcher.matches()) {
					int aid = Integer.parseInt(matcher.group(1));
					String type = matcher.group(2);
					String language = matcher.group(3);
					String title = matcher.group(4);

					if (aid > 0 && title.length() > 0 && languageOrder.contains(language)) {
						List<Object[]> names = entriesByAnime.get(aid);
						if (names == null) {
							names = new ArrayList<Object[]>();
							entriesByAnime.put(aid, names);
						}

						// resolve HTML entities
						title = Jsoup.parse(title).text();

						names.add(new Object[] { Integer.parseInt(type), languageOrder.indexOf(language), title });
					}
				}
			}
		} finally {
			scanner.close();
		}

		// build up a list of all possible AniDB search results
		anime = new ArrayList<AnidbSearchResult>(entriesByAnime.size());

		for (Entry<Integer, List<Object[]>> entry : entriesByAnime.entrySet()) {
			int aid = entry.getKey();
			List<Object[]> triples = entry.getValue();

			Collections.sort(triples, new Comparator<Object[]>() {

				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public int compare(Object[] a, Object[] b) {
					for (int i = 0; i < a.length; i++) {
						if (!a[i].equals(b[i]))
							return ((Comparable) a[i]).compareTo(b[i]);
					}
					return 0;
				}
			});

			List<String> names = new ArrayList<String>(triples.size());
			for (Object[] it : triples) {
				names.add((String) it[2]);
			}

			String primaryTitle = names.get(0);
			String[] aliasNames = names.subList(1, names.size()).toArray(new String[0]);
			anime.add(new AnidbSearchResult(aid, primaryTitle, aliasNames));
		}

		// populate cache
		return cache.putSearchResult(null, Locale.ROOT, anime);
	}