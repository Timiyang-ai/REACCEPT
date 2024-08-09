@VisibleForTesting
    @Override
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        mLocale = context.getResources().getConfiguration().getLocales().get(0);
        mTimeZoneInfoFormatter = new TimeZoneInfo.Formatter(mLocale, new Date());
        final List<AbstractPreferenceController> controllers = new ArrayList<>();
        RegionPreferenceController regionPreferenceController =
                new RegionPreferenceController(context);
        regionPreferenceController.setOnClickListener(this::startRegionPicker);
        RegionZonePreferenceController regionZonePreferenceController =
                new RegionZonePreferenceController(context);
        regionZonePreferenceController.setOnClickListener(this::onRegionZonePreferenceClicked);
        TimeZoneInfoPreferenceController timeZoneInfoPreferenceController =
                new TimeZoneInfoPreferenceController(context);
        FixedOffsetPreferenceController fixedOffsetPreferenceController =
                new FixedOffsetPreferenceController(context);
        fixedOffsetPreferenceController.setOnClickListener(this::startFixedOffsetPicker);

        controllers.add(regionPreferenceController);
        controllers.add(regionZonePreferenceController);
        controllers.add(timeZoneInfoPreferenceController);
        controllers.add(fixedOffsetPreferenceController);
        return controllers;
    }