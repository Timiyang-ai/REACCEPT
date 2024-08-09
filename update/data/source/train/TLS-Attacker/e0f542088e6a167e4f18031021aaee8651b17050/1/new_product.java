public static List<ICEPoint> readPoints(NamedGroup group) {
        EllipticCurve curve = CurveFactory.getCurve(group);
        String namedCurveLow = group.name().toLowerCase();
        String fileName = "points_" + namedCurveLow + ".txt";

        BufferedReader br = new BufferedReader(new InputStreamReader(ICEPointReader.class.getClassLoader()
                .getResourceAsStream(fileName)));
        String line;
        List<ICEPoint> points = new LinkedList<>();
        try {
            while ((line = br.readLine()) != null) {
                if (line.length() != 0 && !line.startsWith("#")) {
                    String[] nums = line.split("\\s+,\\s+");
                    int order = Integer.parseInt(nums[0]);
                    BigInteger x = new BigInteger(nums[1], 16);
                    BigInteger y = new BigInteger(nums[2], 16);
                    points.add(new ICEPoint(x, y, curve, order));
                }
            }
            Collections.sort(points, new ICEPointCopmparator());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Using the following curves and points");
                for (ICEPoint p : points) {
                    LOGGER.debug(p.getOrder() + " , " + p.getX().getData().toString(16) + " , "
                            + p.getY().getData().toString(16));
                }
            }
            return points;
        } catch (IOException | NumberFormatException ex) {
            throw new ConfigurationException(ex.getLocalizedMessage(), ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                LOGGER.error("Failed to close stream", ex);
            }
        }
    }