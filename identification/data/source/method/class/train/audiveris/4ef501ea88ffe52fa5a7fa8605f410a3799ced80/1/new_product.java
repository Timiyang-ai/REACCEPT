public void process ()
    {
        final Scale scale = sheet.getScale();
        final int minDistance = scale.toPixels(
                constants.minDistanceFromStaff);
        final int minRunLength = scale.toPixels(
                constants.minRunLength);
        final StaffManager staffManager = sheet.getStaffManager();
        final RunsTableFactory.Filter filter = new RunsTableFactory.Filter()
        {
            @Override
            public boolean check (int x,
                                  int y,
                                  int length)
            {
                // Check that the run stands outside of staves.
                Point center = new Point(x + (length / 2), y);
                StaffInfo staff = staffManager.getStaffAt(center);

                return staff.distanceTo(center) >= minDistance;
            }
        };

        final RunsTable hugeHoriTable = new RunsTableFactory(
                HORIZONTAL,
                sheet.getPicture().getSource(Picture.SourceKey.BINARY),
                minRunLength).createTable("huge-hori", filter);

        final Lag lag = new BasicLag(
                "hHugeLag",
                Orientation.HORIZONTAL);
        final int maxShift = scale.toPixels(constants.maxRunShift);
        final SectionsBuilder sectionsBuilder = new SectionsBuilder(
                lag,
                new JunctionShiftPolicy(maxShift));

        sectionsBuilder.createSections(hugeHoriTable, true);

        sheet.setLag(Lags.FULL_HLAG, lag);
        sheet.getSystemManager()
                .dispatchHorizontalHugeSections();

        if (Main.getGui() != null) {
            // Display a view on this lag
            HoriController horiController = new HoriController(sheet, lag);
            horiController.refresh();
        }
    }