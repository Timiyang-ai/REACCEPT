@Ignore
	@Test
	public void test01SaveModel() throws ClassNotFoundException, LibrecException, IOException {
		Configuration conf = new Configuration();
		Configuration.Resource resource = new Resource("rec/cf/userknn-test.properties");
		conf.addResource(resource);
		DataModel dataModel = new TextDataModel(conf);
		dataModel.buildDataModel();
		RecommenderContext context = new RecommenderContext(conf, dataModel);
		RecommenderSimilarity similarity = new PCCSimilarity();
		// similarity.setConf(conf);
		// similarity.buildSimilarityMatrix(dataModel, true);
		context.setSimilarity(similarity);
		Recommender recommender = new UserKNNRecommender();
		recommender.recommend(context);
		String filePath = conf.get("dfs.result.dir") + "/model-"
				+ DriverClassUtil.getDriverName(UserKNNRecommender.class);
		recommender.saveModel(filePath);
	}