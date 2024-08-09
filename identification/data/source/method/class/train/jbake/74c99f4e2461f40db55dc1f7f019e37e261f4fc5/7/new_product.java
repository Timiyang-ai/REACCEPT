public void crawl(File path) {
		File[] contents = path.listFiles(FileUtil.getFileFilter());
		if (contents != null) {
			Arrays.sort(contents);
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].isFile()) {
					System.out.print("Processing [" + contents[i].getPath() + "]... ");
					Map<String, Object> fileContents = parser.processFile(contents[i]);
					if (fileContents != null) {
						fileContents.put("file", contents[i].getPath());
						String uri = contents[i].getPath().replace(contentPath, "");
						uri = uri.substring(0, uri.lastIndexOf("."));
						fileContents.put("uri", uri+config.getString("output.extension"));
						
						if (fileContents.get("type").equals("post")) {
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
                     if (fileContents.get("status").equals("published-date")) {
                        if (fileContents.get("date") != null && (fileContents.get("date") instanceof Date)) {
                           if (new Date().after((Date)fileContents.get("date"))) {
                              fileContents.put("status", "published");
                           }
                        }
                     }
						} else {// everything else is considered a page
							pages.add(fileContents);
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