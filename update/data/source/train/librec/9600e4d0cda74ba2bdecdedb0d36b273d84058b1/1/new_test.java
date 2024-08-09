@Ignore
	@Test
	public void test02LoadModel() throws ClassNotFoundException, LibrecException, IOException {
		Configuration conf = new Configuration();
		Configuration.Resource resource = new Resource("rec/cf/userknn-test.properties");
		conf.addResource(resource);
		// DataModel dataModel = new TextDataModel(conf);
		// dataModel.buildDataModel();
		RecommenderContext context = new RecommenderContext(conf);
		// RecommenderSimilarity similarity = new PCCSimilarity();
		// similarity.setConf(conf);
		// similarity.buildSimilarityMatrix(dataModel, true);
		// context.setSimilarity(similarity);
		Recommender recommender = new UserKNNRecommender();
		recommender.setContext(context);
		String filePath = conf.get("dfs.result.dir") + "/model-"
				+ DriverClassUtil.getDriverName(UserKNNRecommender.class);
		recommender.loadModel(filePath);
//		recommender.recommend(context); // TODO: 21/04/2018
//		List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
//		RecommendedFilter filter = new GenericRecommendedFilter();
//		recommendedItemList = filter.filter(recommendedItemList);
	}