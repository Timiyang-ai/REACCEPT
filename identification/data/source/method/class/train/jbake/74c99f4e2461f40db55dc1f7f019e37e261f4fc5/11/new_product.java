public void crawl(File path) {
		Parser parser = new Parser();
		File[] contents = path.listFiles(FileUtil.getFileFilter());
		if (contents != null) {
			Arrays.sort(contents);
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].isFile()) {
					System.out.print("Processing [" + contents[i].getPath() + "]... ");
					Map<String, Object> fileContents = parser.processFile(contents[i]);
					if (fileContents != null) {
						fileContents.put("file", contents[i].getPath());
						fileContents.put("uri", contents[i].getPath().replace(source.getPath() + File.separator + config.getString("content.folder"), ""));
						
						if (fileContents.get("type").equals("page")) {
							pages.add(fileContents);
						} else {
							// everything else is considered a post
							posts.add(fileContents);
							
							if (fileContents.get("tags") != null) {
								String[] tags = (String[]) fileContents.get("tags");
								for (String tag : tags) {
									if (postsByTags.containsKey(tag)) {
										postsByTags.get(tag).add(fileContents);
									} else {
										List<Map<String, Object>> posts = new ArrayList<Map<String, Object>>();
										posts.add(fileContents);
										postsByTags.put(tag, posts);
									}
								}
							}
						}
						System.out.println("done!");
					}
				} 
				
				if (contents[i].isDirectory()) {
					crawl(contents[i]);
				}
			}
		}
	}