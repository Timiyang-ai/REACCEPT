public static JavaPairRDD<Point, List<Point>> SpatialJoinQueryUsingIndex(JavaSparkContext sc, PointRDD pointRDD1, PointRDD pointRDD2, Double distance) {
        //Grid filter, Maybe we can filter those key doesn't overlap the destination.

        //Just use grid of Convert pointRDD2 to CircleRDD.
        CircleRDD circleRDD2 = new CircleRDD(pointRDD2, distance);

        //Build grid on circleRDD2.

        final Broadcast<List<Envelope>> envelopeWithGrid = sc.broadcast(pointRDD1.grids);

        JavaPairRDD<Integer, Circle> tmpGridedCircleForQuerySetBeforePartition = circleRDD2.rawSpatialRDD.flatMapToPair(new PairFlatMapFunction<Object, Integer, Circle>() {
            @Override
            public Iterable<Tuple2<Integer, Circle>> call(Object spatialObject) throws Exception {
            	Circle circle = (Circle) spatialObject;
            	HashSet<Tuple2<Integer, Circle>> result = new HashSet<Tuple2<Integer, Circle>>();
            	List<Envelope> grid = envelopeWithGrid.getValue();

                for (int i=0;i<grid.size();i++) {
                    try {
                        if (circle.intersects(grid.get(i))) {
                            result.add(new Tuple2<Integer, Circle>(i, circle));
                        }
                    } catch (NullPointerException exp) {
                        System.out.println(grid.get(i).toString() + circle.toString());
                    }
                }
                return result;

            }
        });

        JavaPairRDD<Integer, Circle> tmpGridRDDForQuerySet = tmpGridedCircleForQuerySetBeforePartition.partitionBy(pointRDD1.spatialPartitionedRDD.partitioner().get()).persist(StorageLevel.DISK_ONLY());

        JavaPairRDD<Integer, Tuple2<Iterable<Object>, Iterable<Circle>>> cogroupResult = pointRDD1.indexedRDD.cogroup(tmpGridRDDForQuerySet);

        JavaPairRDD<Point, HashSet<Point>> joinResultBeforeAggregation = cogroupResult.flatMapToPair(new PairFlatMapFunction<Tuple2<Integer, Tuple2<Iterable<Object>, Iterable<Circle>>>, Point, HashSet<Point>>() {
            @Override
            public Iterable<Tuple2<Point, HashSet<Point>>> call(Tuple2<Integer, Tuple2<Iterable<Object>, Iterable<Circle>>> cogroup) throws Exception {
            	HashSet<Tuple2<Point, HashSet<Point>>> result = new HashSet<Tuple2<Point, HashSet<Point>>>();
                SpatialIndex treeIndex=(SpatialIndex) cogroup._2()._1().iterator().next();
                if(treeIndex instanceof STRtree)
                {
                	treeIndex = (STRtree)treeIndex;
                }
                else
                {
                	treeIndex = (Quadtree)treeIndex;
                }
                for (Object c : cogroup._2()._2()) {
                    List<Point> pointList = new ArrayList<Point>();

                    pointList = treeIndex.query(((Circle)c).getMBR());
                    HashSet<Point> pointSet = new HashSet<Point>(pointList);
                    result.add(new Tuple2<Point, HashSet<Point>>(((Circle)c).getCenter(), pointSet));
                }
                return result;
            }

        });


        //AggregateByKey?
        JavaPairRDD<Point, HashSet<Point>> joinResultAfterAggregation = joinResultBeforeAggregation.reduceByKey(new Function2<HashSet<Point>, HashSet<Point>, HashSet<Point>>() {
            @Override
            public HashSet<Point> call(HashSet<Point> points, HashSet<Point> points2) throws Exception {
                points.addAll(points2);
                return points;
            }
        });

        JavaPairRDD<Point, List<Point>> joinListResultAfterAggregation = joinResultAfterAggregation.mapValues(new Function<HashSet<Point>, List<Point>>() {
            @Override
            public List<Point> call(HashSet<Point> points) throws Exception {
                return new ArrayList<Point>(points);
            }
        });

        return joinListResultAfterAggregation;
    }