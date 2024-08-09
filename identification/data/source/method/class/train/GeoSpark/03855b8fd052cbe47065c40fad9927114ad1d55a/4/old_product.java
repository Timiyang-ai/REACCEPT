public static JavaPairRDD<Point, List<Point>> SpatialJoinQueryUsingIndex(JavaSparkContext sc, PointRDD pointRDD1, PointRDD pointRDD2, Double distance) {
        //Grid filter, Maybe we can filter those key doesn't overlap the destination.

        //Just use grid of Convert pointRDD2 to CircleRDD.
        CircleRDD circleRDD2 = new CircleRDD(pointRDD2, distance);

        //Build grid on circleRDD2.

        final Broadcast<HashSet<EnvelopeWithGrid>> envelopeWithGrid = sc.broadcast(pointRDD1.grids);

        JavaPairRDD<Integer, Circle> tmpGridedCircleForQuerySetBeforePartition = circleRDD2.getCircleRDD().flatMapToPair(new PairFlatMapFunction<Circle, Integer, Circle>() {
            @Override
            public Iterable<Tuple2<Integer, Circle>> call(Circle circle) throws Exception {
            	HashSet<Tuple2<Integer, Circle>> result = new HashSet<Tuple2<Integer, Circle>>();

                HashSet<EnvelopeWithGrid> grid = envelopeWithGrid.getValue();

                for (EnvelopeWithGrid e : grid) {
                    if (circle.intersects(e)) {
                        result.add(new Tuple2<Integer, Circle>(e.grid, circle));
                    }
                }
                return result;

            }
        });

        JavaPairRDD<Integer, Circle> tmpGridRDDForQuerySet = tmpGridedCircleForQuerySetBeforePartition.partitionBy(pointRDD1.gridPointRDD.partitioner().get()).persist(StorageLevel.DISK_ONLY());

        JavaPairRDD<Integer, Tuple2<Iterable<STRtree>, Iterable<Circle>>> cogroupResult = pointRDD1.indexedRDD.cogroup(tmpGridRDDForQuerySet);

        JavaPairRDD<Point, HashSet<Point>> joinResultBeforeAggregation = cogroupResult.flatMapToPair(new PairFlatMapFunction<Tuple2<Integer, Tuple2<Iterable<STRtree>, Iterable<Circle>>>, Point, HashSet<Point>>() {
            @Override
            public Iterable<Tuple2<Point, HashSet<Point>>> call(Tuple2<Integer, Tuple2<Iterable<STRtree>, Iterable<Circle>>> cogroup) throws Exception {
            	HashSet<Tuple2<Point, HashSet<Point>>> result = new HashSet<Tuple2<Point, HashSet<Point>>>();

                Tuple2<Iterable<STRtree>, Iterable<Circle>> cogroupTupleList = cogroup._2();
                for (Circle c : cogroupTupleList._2()) {
                    List<Point> pointList = new ArrayList<Point>();
                    for (STRtree s : cogroupTupleList._1()) {
                        //这可以? 他都不知道类型把..
                        pointList = s.query(c.getMBR());

                        //This is not enough, need to verify again.

                    }
                    HashSet<Point> pointSet = new HashSet<Point>(pointList);
                    result.add(new Tuple2<Point, HashSet<Point>>(c.getCenter(), pointSet));
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