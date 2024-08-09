public void process( LookupSimilarImages similarImages ) {
		this.imageIds = similarImages.getImageIDs();
		this.graph.reset();

		List<String> similar = new ArrayList<>();
		FastQueue<Point2D_F64> srcFeats = new FastQueue<>(Point2D_F64.class,true);
		FastQueue<Point2D_F64> dstFeats = new FastQueue<>(Point2D_F64.class,true);
		FastQueue<AssociatedIndex> matches = new FastQueue<>(AssociatedIndex.class,true);
		FastQueue<AssociatedPair> pairs = new FastQueue<>(AssociatedPair.class,true);

		// map to quickly look up the ID of a view
		Map<String,Integer> imageToindex = new HashMap<>();

		// Create a node in the graph for each image
		for (int idxTgt = 0; idxTgt < imageIds.size(); idxTgt++) {
			imageToindex.put(imageIds.get(idxTgt),idxTgt);
			graph.createNode(imageIds.get(idxTgt));
		}

		// For each image examine all related images for a true geometric relationship
		// if one exists then add an edge to the graph describing their relationship
		for (int idxTgt = 0; idxTgt < imageIds.size(); idxTgt++) {
			String src = imageIds.get(idxTgt);

			similarImages.findSimilar(src,similar);
			similarImages.lookupFeatures(src,srcFeats);

			graph.nodes.get(idxTgt).totalFeatures = srcFeats.size;

			for (int idxSimilar = 0; idxSimilar < similar.size(); idxSimilar++) {
				String dst = similar.get(idxSimilar);

				// make sure it isn't considering the same motion twice
				int dstIdx = imageToindex.get(dst);
				if( dstIdx <= idxTgt )
					continue;

				// get information on the features and association
				similarImages.lookupFeatures(dst,dstFeats);
				similarImages.lookupMatches(src,dst,matches);

				pairs.reset();
				for (int i = 0; i < matches.size; i++) {
					AssociatedIndex m = matches.get(i);
					pairs.grow().set(srcFeats.get(m.src),dstFeats.get(m.dst));
				}

				createEdge(src,dst,pairs,matches);
			}
		}
	}