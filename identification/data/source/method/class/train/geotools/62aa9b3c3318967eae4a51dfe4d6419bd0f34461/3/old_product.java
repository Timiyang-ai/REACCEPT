public static GridCoverage2D reproject(GridCoverage2D            sourceCoverage,
                                           CoordinateReferenceSystem targetCRS,
                                           GridGeometry2D            targetGG,
                                           Interpolation             interpolation,
                                           final Hints               hints,
                                           final double[]            backgroundValues)
            throws FactoryException, TransformException
    {
        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        //// =======>>  STEP 1: Extracts needed informations from the parameters   <<====== ////
        ////            STEP 2: Creates the "target to source" MathTransform                ////
        ////            STEP 3: Computes the target image layout                            ////
        ////            STEP 4: Applies the JAI operation ("Affine", "Warp", etc)           ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////
        Utilities.ensureNonNull("sourceCoverage", sourceCoverage);
        // CRS
        final CoordinateReferenceSystem sourceCRS = sourceCoverage.getCoordinateReferenceSystem();
        if (targetCRS == null) {
            // in case the TargetCRS has not been set we try to use the CRS that was part of the TargetGG
            if(targetGG!=null&&targetGG.isDefined(GridGeometry2D.CRS_BITMASK)){
                targetCRS=targetGG.getCoordinateReferenceSystem();
            }else{
                // in case the TargetCRS is not set and either the TargetGG is not set
                // or it has not CRS inside, we reuse sourceCRS 
                targetCRS = sourceCRS;
            }
            // From this point, consider "targetCRS" as final.
        }else{
            // in case the targetCRS is set we should check that it is compatible
            // with the targetGG crs, otherwise we throw an exception
            if(targetGG!=null&&targetGG.isDefined(GridGeometry2D.CRS_BITMASK)){
                final CoordinateReferenceSystem targetGGCRS=targetGG.getCoordinateReferenceSystem();
                if(!CRS.equalsIgnoreMetadata(targetCRS, targetGGCRS)&&!CRS.findMathTransform(targetCRS, targetGGCRS).isIdentity()){
                    throw new IllegalArgumentException(Errors.format(ErrorKeys.ILLEGAL_ARGUMENT_$1,"TargetCRS must be compatible with TargetGG CRS"));
                }
                
            }
        }
        
        //
        // INTERPOLATION MANAGEMENT as well as BORDER_EXTENDER
        //
        if(interpolation==null){

            //if we did not the interpolation, let's try to get it from hints
            if(hints!=null){
                // JAI interpolation 
                if(hints.containsKey(JAI.KEY_INTERPOLATION))
                        interpolation=(Interpolation) hints.get(JAI.KEY_INTERPOLATION);
            }
        }else{
            // we have been provided with interpolation, let's override hints
            hints.put(JAI.KEY_INTERPOLATION,interpolation);
        }
        if (!hints.containsKey(JAI.KEY_BORDER_EXTENDER)) {
            hints.put(JAI.KEY_BORDER_EXTENDER, BorderExtender.createInstance(BorderExtender.BORDER_COPY));
        }
        
        /*
         * The following will tell us if the target GridRange (GR) and GridGeometry (GG) should
         * be computed automatically, or if we should follow strictly what the user said. Note
         * that "automaticGG" implies "automaticGR" but the converse is not necessarily true.
         */
        final boolean automaticGG, automaticGR;
        
        /*
         * Grid range and "grid to CRS" transform are the only grid geometry informations used
         * by this method. If they are not available, this is equivalent to not providing grid
         * geometry at all. In such case set to 'targetGG' reference to null, since null value
         * is what the remaining code will check for.
         */
        if (targetGG == null) {
            automaticGG = true;
            automaticGR = true;
        } else {
            automaticGR = !targetGG.isDefined(GridGeometry2D.GRID_RANGE_BITMASK);
            if (!automaticGR || targetGG.isDefined(GridGeometry2D.GRID_TO_CRS_BITMASK)) {
                automaticGG = false;
            } else {
                /*
                 * Before to abandon the grid geometry, checks if it contains an envelope (note:
                 * we really want it in sourceCRS, not targetCRS - the reprojection will be done
                 * later in this method). If so, we will recreate a new grid geometry from the
                 * envelope using the same "grid to CRS" transform than the original coverage.
                 * The result may be an image with a different size.
                 */
                if (targetGG.isDefined(GridGeometry2D.ENVELOPE_BITMASK)) {
                    final Envelope       envelope = targetGG.getEnvelope();
                    final GridGeometry2D sourceGG = sourceCoverage.getGridGeometry();
                    final MathTransform  gridToCRS;
                    switch (envelope.getDimension()) {
                        case 2:  gridToCRS = sourceGG.getGridToCRS2D(CORNER); break;
                        default: gridToCRS = sourceGG.getGridToCRS(CORNER);   break;
                    }
                    targetGG = new GridGeometry2D(PixelInCell.CELL_CENTER, gridToCRS, envelope, null);
                    automaticGG = false;
                } else {
                    targetGG = null;
                    automaticGG = true;
                }
            }
        }
        /*
         * If the source coverage is already the result of a previous "Resample" operation,
         * go up in the chain and check if a previously computed image could fits (i.e. the
         * requested resampling may be the inverse of a previous resampling). This method
         * may stop immediately if a suitable image is found.
         */
        GridCoverage2D targetCoverage = existingCoverage(sourceCoverage, targetCRS, targetGG);
        if (targetCoverage != null) {
            return targetCoverage;
        }
        final GridGeometry2D sourceGG = sourceCoverage.getGridGeometry();
        final CoordinateReferenceSystem compatibleSourceCRS = compatibleSourceCRS(
                sourceCoverage.getCoordinateReferenceSystem2D(), sourceCRS, targetCRS);
        /*
         * The projection are usually applied on floating-point values, in order
         * to gets maximal precision and to handle correctly the special case of
         * NaN values. However, we can apply the projection on integer values if
         * the interpolation type is "nearest neighbor", since this is not really
         * an interpolation.
         *
         * If this condition is meets, then we verify if an "integer version" of the image
         * is available as a source of the source coverage (i.e. the floating-point image
         * is derived from the integer image, not the converse).
         */
        final ViewType processingView = CoverageUtilities.preferredViewForOperation(
                                        sourceCoverage, interpolation, false, hints);
        final ViewType finalView = CoverageUtilities.preferredViewAfterOperation(sourceCoverage);
        sourceCoverage = sourceCoverage.view(processingView);
        PlanarImage sourceImage = PlanarImage.wrapRenderedImage(sourceCoverage.getRenderedImage());
        assert sourceCoverage.getCoordinateReferenceSystem() == sourceCRS : sourceCoverage;
        // From this point, consider 'sourceCoverage' as final.

        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        ////            STEP 1: Extracts needed informations from the parameters            ////
        //// =======>>  STEP 2: Creates the "target to source" MathTransform       <<====== ////
        ////            STEP 3: Computes the target image layout                            ////
        ////            STEP 4: Applies the JAI operation ("Affine", "Warp", etc)           ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////

        final CoordinateOperationFactory factory =
                ReferencingFactoryFinder.getCoordinateOperationFactory(hints);
        final MathTransformFactory mtFactory;
        if (factory instanceof AbstractCoordinateOperationFactory) {
            mtFactory = ((AbstractCoordinateOperationFactory) factory).getMathTransformFactory();
        } else {
            mtFactory = ReferencingFactoryFinder.getMathTransformFactory(hints);
        }
        /*
         * Computes the INVERSE of the math transform from [Source Grid] to [Target Grid].
         * The transform will be computed using the following path:
         *
         *      Target Grid --> Target CRS --> Source CRS --> Source Grid
         *                   ^              ^              ^
         *                 step 1         step 2         step 3
         *
         * If source and target CRS are equal, a shorter path is used. This special
         * case is needed because 'sourceCRS' and 'targetCRS' may be null.
         *
         *      Target Grid --> Common CRS --> Source Grid
         *                   ^              ^
         *                 step 1         step 3
         */
        final MathTransform step1, step2, step3, allSteps, allSteps2D;
        if (CRS.equalsIgnoreMetadata(sourceCRS, targetCRS)) {
            /*
             * Note: targetGG should not be null, otherwise 'existingCoverage(...)' should
             *       have already detected that this resample is not doing anything.
             */
            if (!targetGG.isDefined(GridGeometry2D.GRID_TO_CRS_BITMASK)) {
                step1    = sourceGG.getGridToCRS(CORNER); // Really sourceGG, not targetGG
                step2    = IdentityTransform.create(step1.getTargetDimensions());
                step3    = step1.inverse();
                allSteps = IdentityTransform.create(step1.getSourceDimensions());
                targetGG = new GridGeometry2D(targetGG.getGridRange(), step1, targetCRS);
            } else {
                step1    = targetGG.getGridToCRS(CORNER);
                step2    = IdentityTransform.create(step1.getTargetDimensions());
                step3    = sourceGG.getGridToCRS(CORNER).inverse();
                allSteps = mtFactory.createConcatenatedTransform(step1, step3);
                if (!targetGG.isDefined(GridGeometry2D.GRID_RANGE_BITMASK)) {
                    /*
                     * If the target grid range was not explicitly specified, a grid range will be
                     * automatically computed in such a way that it will maps to the same envelope
                     * (at least approximatively).
                     */
                    Envelope gridRange;
                    gridRange = toEnvelope(sourceGG.getGridRange());
                    gridRange = CRS.transform(allSteps.inverse(), gridRange);
                    targetGG  = new GridGeometry2D(new GeneralGridEnvelope(gridRange,PixelInCell.CELL_CORNER), targetGG.getGridToCRS(PixelInCell.CELL_CENTER), targetCRS);
                }
            }
        } else {
            if (sourceCRS == null) {
                throw new CannotReprojectException(Errors.format(ErrorKeys.UNSPECIFIED_CRS));
            }
            final Envelope        sourceEnvelope;
            final GeneralEnvelope targetEnvelope;
            final CoordinateOperation operation = factory.createOperation(sourceCRS, targetCRS);
            final boolean force2D = (sourceCRS != compatibleSourceCRS);
            step2          = factory.createOperation(targetCRS, compatibleSourceCRS).getMathTransform();
            step3          = (force2D ? sourceGG.getGridToCRS2D(CORNER) : sourceGG.getGridToCRS(CORNER)).inverse();
            sourceEnvelope = sourceCoverage.getEnvelope(); // Don't force this one to 2D.
            targetEnvelope = CRS.transform(operation, sourceEnvelope);
            targetEnvelope.setCoordinateReferenceSystem(targetCRS);
            // 'targetCRS' may be different than the one set by CRS.transform(...).
            /*
             * If the target GridGeometry is incomplete, provides default
             * values for the missing fields. Three cases may occurs:
             *
             * - User provided no GridGeometry at all. Then, constructs an image of the same size
             *   than the source image and set an envelope big enough to contains the projected
             *   coordinates. The transform will derive from the grid range and the envelope.
             *
             * - User provided only a grid range.  Then, set an envelope big enough to contains
             *   the projected coordinates. The transform will derive from the grid range and
             *   the envelope.
             *
             * - User provided only a "grid to CRS" transform. Then, transform the projected
             *   envelope to "grid units" using the specified transform and create a grid range
             *   big enough to hold the result.
             */
            if (targetGG == null) {
                final GridEnvelope targetGR;
                targetGR = force2D ? new GridEnvelope2D(sourceGG.getGridRange2D()) : sourceGG.getGridRange();
                targetGG = new GridGeometry2D(targetGR, targetEnvelope);
                step1    = targetGG.getGridToCRS(CORNER);
            } else if (!targetGG.isDefined(GridGeometry2D.GRID_TO_CRS_BITMASK)) {
                targetGG = new GridGeometry2D(targetGG.getGridRange(), targetEnvelope);
                step1    = targetGG.getGridToCRS(CORNER);
            } else {
                step1 = targetGG.getGridToCRS(CORNER);
                if (!targetGG.isDefined(GridGeometry2D.GRID_RANGE_BITMASK)) {
                    GeneralEnvelope gridRange = CRS.transform(step1.inverse(), targetEnvelope);
                    // According OpenGIS specification, GridGeometry maps pixel's center.
                    targetGG = new GridGeometry2D(new GeneralGridEnvelope(gridRange, PixelInCell.CELL_CENTER), step1, targetCRS);
                }
            }
            /*
             * Computes the final transform.
             */
            allSteps = mtFactory.createConcatenatedTransform(
                       mtFactory.createConcatenatedTransform(step1, step2), step3);
        }
        allSteps2D = toMathTransform2D(allSteps, mtFactory, targetGG);
        if (!(allSteps2D instanceof MathTransform2D)) {
            // Should not happen with Geotools implementations. May happen
            // with some external implementations, but should stay unusual.
            throw new TransformException(Errors.format(ErrorKeys.NO_TRANSFORM2D_AVAILABLE));
        }

        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        ////            STEP 1: Extracts needed informations from the parameters            ////
        ////            STEP 2: Creates the "target to source" MathTransform                ////
        //// =======>>  STEP 3: Computes the target image layout                   <<====== ////
        ////            STEP 4: Applies the JAI operation ("Affine", "Warp", etc)           ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////

        final RenderingHints targetHints = processingView.getRenderingHints(sourceImage);
        if (hints != null) {
            targetHints.add(hints);
        }
        ImageLayout layout = (ImageLayout) targetHints.get(JAI.KEY_IMAGE_LAYOUT);
        if (layout != null) {
            layout = (ImageLayout) layout.clone();
        } else {
            layout = new ImageLayout();
            // Do not inherit the color model and sample model from the 'sourceImage';
            // Let the operation decide itself. This is necessary in case we change the
            // source, as we do if we choose the "Mosaic" operation.
        }
        final Rectangle sourceBB = sourceGG.getGridRange2D();
        final Rectangle targetBB = targetGG.getGridRange2D();
        if (isBoundsUndefined(layout, false)) {
            layout.setMinX  (targetBB.x);
            layout.setMinY  (targetBB.y);
            layout.setWidth (targetBB.width);
            layout.setHeight(targetBB.height);
        }
        if (isBoundsUndefined(layout, true)) {
            Dimension size = new Dimension(layout.getWidth (sourceImage),
                                           layout.getHeight(sourceImage));
            size = ImageUtilities.toTileSize(size);
            layout.setTileGridXOffset(layout.getMinX(sourceImage));
            layout.setTileGridYOffset(layout.getMinY(sourceImage));
            layout.setTileWidth (size.width);
            layout.setTileHeight(size.height);
        }
        /*
         * Creates the background values array. 
         */
        final double[] background = backgroundValues != null ? backgroundValues : CoverageUtilities.getBackgroundValues(sourceCoverage);

        
        /*
         * We need to correctly manage the Hints to control the replacement of IndexColorModel.
         * It is worth to point out that setting the JAI.KEY_REPLACE_INDEX_COLOR_MODEL hint to
         * Boolean.TRUE is not enough to force the operators to do an expansion. If we explicitly
         * provide an ImageLayout built with the source image where the CM and the SM are valid.
         * those will be employed overriding a the possibility to expand the color model.
         */
        if (ViewType.PHOTOGRAPHIC.equals(processingView)) {
            layout.unsetValid(ImageLayout.COLOR_MODEL_MASK | ImageLayout.SAMPLE_MODEL_MASK);
        }
        targetHints.put(JAI.KEY_IMAGE_LAYOUT, layout);

        ////////////////////////////////////////////////////////////////////////////////////////
        ////                                                                                ////
        ////            STEP 1: Extracts needed informations from the parameters            ////
        ////            STEP 2: Creates the "target to source" MathTransform                ////
        ////            STEP 3: Computes the target image layout                            ////
        //// =======>>  STEP 4: Applies the JAI operation ("Affine", "Warp", etc)  <<====== ////
        ////                                                                                ////
        ////////////////////////////////////////////////////////////////////////////////////////
        /*
         * If the user requests a new grid geometry with the same coordinate reference system,
         * and if the grid geometry is equivalents to a simple extraction of a sub-area, then
         * delegates the work to a "Crop" operation.
         */
        final String operation;
        final ParameterBlock paramBlk = new ParameterBlock().addSource(sourceImage);
        final Map<String, Object> imageProperties = new HashMap<String, Object>();
        Warp warp = null;
        if (allSteps.isIdentity() || (allSteps instanceof AffineTransform &&
                XAffineTransform.isIdentity((AffineTransform) allSteps, EPS)))
        {
            /*
             * Since there is no interpolation to perform, use the native view (which may be
             * packed or geophysics - it is just the view which is closest to original data).
             */
            sourceCoverage = sourceCoverage.view(ViewType.NATIVE);
            sourceImage = PlanarImage.wrapRenderedImage(sourceCoverage.getRenderedImage());
            paramBlk.removeSources();
            paramBlk.addSource(sourceImage);
            if (targetBB.equals(sourceBB)) {
                /*
                 * Optimization in case we have nothing to do, not even a crop. Reverts to the
                 * original coverage BEFORE to creates Resampler2D. Note that while there is
                 * nothing to do, the target CRS is not identical to the source CRS (so we need
                 * to create a new coverage) otherwise this condition would have been detected
                 * sooner in this method.
                 */
                sourceCoverage = sourceCoverage.view(finalView);
                sourceImage = PlanarImage.wrapRenderedImage(sourceCoverage.getRenderedImage());
                return create(sourceCoverage, sourceImage, targetGG, ViewType.SAME, null, null, hints);
            }
            if (sourceBB.contains(targetBB)) {
                operation = "Crop";
                paramBlk.add(Float.valueOf(targetBB.x))
                        .add(Float.valueOf(targetBB.y))
                        .add(Float.valueOf(targetBB.width))
                        .add(Float.valueOf(targetBB.height));
            } else {
                operation = "Mosaic";
                paramBlk.add(MosaicDescriptor.MOSAIC_TYPE_OVERLAY)
                        .add(null).add(null).add(null).add(background);
            }
        } else {
            /*
             * Special case for the affine transform. Try to use the JAI "Affine" operation
             * instead of the more general "Warp" one. JAI provides native acceleration for
             * the affine operation.
             *
             * NOTE 1: There is no need to check for "Scale" and "Translate" as special cases
             *         of "Affine" since JAI already does this check for us.
             *
             * NOTE 2: "Affine", "Scale", "Translate", "Rotate" and similar operations ignore
             *         the 'xmin', 'ymin', 'width' and 'height' image layout. Consequently, we
             *         can't use this operation if the user provided explicitly a grid range.
             *
             * NOTE 3: If the user didn't specified any grid geometry, then a yet cheaper approach
             *         is to just update the 'gridToCRS' value. We returns a grid coverage wrapping
             *         the SOURCE image with the updated grid geometry.
             */
            if ((automaticGR || targetBB.equals(sourceBB)) && allSteps instanceof AffineTransform) {
                if (automaticGG) {
                    // Cheapest approach: just update 'gridToCRS'.
                    MathTransform mtr;
                    mtr = sourceGG.getGridToCRS(CORNER);
                    mtr = mtFactory.createConcatenatedTransform(mtr,  step2.inverse());
                    targetGG = new GridGeometry2D(sourceGG.getGridRange(), mtr, targetCRS);
                    /*
                     * Note: do NOT use the "GridGeometry2D(sourceGridRange, targetEnvelope)"
                     * constructor in the above line. We must give a MathTransform argument to
                     * the constructor, not an Envelope, because the later infer a MathTransform
                     * using heuristic rules. Only the constructor with a MathTransform argument
                     * is fully accurate.
                     */
                    return create(sourceCoverage, sourceImage, targetGG, finalView, null, null, hints);
                }
                // More general approach: apply the affine transform.
                operation = "Affine";
                final AffineTransform affine = (AffineTransform) allSteps.inverse();
                paramBlk.add(affine).add(interpolation).add(background);
            } else {
                /*
                 * General case: constructs the warp transform.
                 *
                 * TODO: JAI 1.1.3 seems to have a bug when the target envelope is greater than
                 *       the source envelope:  Warp on float values doesn't set to 'background'
                 *       the points outside the envelope. The operation seems to work correctly
                 *       on integer values, so as a workaround we restart the operation without
                 *       interpolation (which increase the chances to get it down on integers).
                 *       Remove this hack when this JAI bug will be fixed.
                 *
                 * TODO: Move the check for AffineTransform into WarpTransform2D.
                 */
                boolean forceAdapter = false;
                switch (sourceImage.getSampleModel().getTransferType()) {
                    case DataBuffer.TYPE_DOUBLE:
                    case DataBuffer.TYPE_FLOAT: {
                        Envelope source = CRS.transform(sourceGG.getEnvelope(), targetCRS);
                        Envelope target = CRS.transform(targetGG.getEnvelope(), targetCRS);
                        source = targetGG.reduce(source);
                        target = targetGG.reduce(target);
                        if (!(new GeneralEnvelope(source).contains(target, true))) {
                            if (interpolation != null && !(interpolation instanceof InterpolationNearest)) {
                                return reproject(sourceCoverage, targetCRS, targetGG, null, hints, background);
                            } else {
                                // If we were already using nearest-neighbor interpolation, force
                                // usage of WarpAdapter2D instead of WarpAffine. The price will be
                                // a slower reprojection.
                                forceAdapter = true;
                            }
                        }
                    }
                }
                // -------- End of JAI bug workaround --------
                final MathTransform2D transform = (MathTransform2D) allSteps2D;
                final CharSequence name = sourceCoverage.getName();
                operation = "Warp";
                if (forceAdapter) {
                    warp = new WarpBuilder(0.0).buildWarp(transform, sourceBB);
                } else {
                    warp = createWarp(name, sourceBB, targetBB, transform, mtFactory, hints);
                }
                // store the transormation in the properties, as we might want to retrieve and chain
                // it with affine transforms down the chain
                imageProperties.put("MathTransform", transform);
                imageProperties.put("SourceBoundingBox", sourceBB);
                paramBlk.add(warp).add(interpolation).add(background);
            }
        }
        final RenderedOp targetImage = getJAI(hints).createNS(operation, paramBlk, targetHints);
        for (Map.Entry<String, Object> entry : imageProperties.entrySet()) {
            targetImage.setProperty(entry.getKey(), entry.getValue());
        }
        final Locale locale = sourceCoverage.getLocale();  // For logging purpose.
        /*
         * The JAI operation sometime returns an image with a bounding box different than what we
         * expected. This is true especially for the "Affine" operation: the JAI documentation said
         * explicitly that xmin, ymin, width and height image layout hints are ignored for this one.
         * As a safety, we check the bounding box in any case. If it doesn't matches, then we will
         * reconstruct the target grid geometry.
         */
        final GridEnvelope targetGR = targetGG.getGridRange();
        final int[] lower = targetGR.getLow().getCoordinateValues();
        final int[] upper = targetGR.getHigh().getCoordinateValues();
        for (int i=0; i<upper.length; i++) {
            upper[i]++; // Make them exclusive.
        }
        lower[targetGG.gridDimensionX] = targetImage.getMinX();
        lower[targetGG.gridDimensionY] = targetImage.getMinY();
        upper[targetGG.gridDimensionX] = targetImage.getMaxX();
        upper[targetGG.gridDimensionY] = targetImage.getMaxY();
        final GridEnvelope actualGR = new GeneralGridEnvelope(lower, upper);
        if (!targetGR.equals(actualGR)) {
        	targetGG = new GridGeometry2D(actualGR, targetGG.getGridToCRS(PixelInCell.CELL_CENTER),targetCRS);
            if (!automaticGR) {
                log(Loggings.getResources(locale).getLogRecord(Level.FINE,
                    LoggingKeys.ADJUSTED_GRID_GEOMETRY_$1, sourceCoverage.getName().toString(locale)));
            }
        }
        /*
         * Constructs the final grid coverage, then log a message as in the following example:
         *
         *     Resampled coverage "Foo" from coordinate system "myCS" (for an image of size
         *     1000x1500) to coordinate system "WGS84" (image size 1000x1500). JAI operation
         *     is "Warp" with "Nearest" interpolation on geophysics pixels values. Background
         *     value is 255.
         */
        targetCoverage = create(sourceCoverage, targetImage, targetGG, finalView, operation, warp, hints);
        assert CRS.equalsIgnoreMetadata(targetCoverage.getCoordinateReferenceSystem(), targetCRS) : targetGG;
        assert targetCoverage.getGridGeometry().getGridRange2D().equals(targetImage.getBounds())  : targetGG;
        if (CoverageProcessor.LOGGER.isLoggable(LOGGING_LEVEL)) {
            log(Loggings.getResources(locale).getLogRecord(LOGGING_LEVEL,
                LoggingKeys.APPLIED_RESAMPLE_$11, new Object[] {
                /*  {0} */ sourceCoverage.getName().toString(locale),
                /*  {1} */ sourceCoverage.getCoordinateReferenceSystem().getName().getCode(),
                /*  {2} */ sourceImage.getWidth(),
                /*  {3} */ sourceImage.getHeight(),
                /*  {4} */ targetCoverage.getCoordinateReferenceSystem().getName().getCode(),
                /*  {5} */ targetImage.getWidth(),
                /*  {6} */ targetImage.getHeight(),
                /*  {7} */ targetImage.getOperationName(),
                /*  {8} */ Integer.valueOf(sourceCoverage == sourceCoverage.view(ViewType.GEOPHYSICS) ? 1 : 0),
                /*  {9} */ ImageUtilities.getInterpolationName(interpolation),
                /* {10} */ (background != null) ? background.length == 1 ? (Double.isNaN(background[0]) ? (Object) "NaN"
                                 : (Object) Double.valueOf(background[0]))
                                 : (Object) XArray.toString(background, locale)
                                 : "No background used" }));
        }
        
        return targetCoverage;
    }