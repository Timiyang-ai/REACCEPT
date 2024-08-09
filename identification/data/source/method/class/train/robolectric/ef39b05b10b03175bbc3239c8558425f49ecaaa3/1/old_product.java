static void applyRules(Configuration configuration, DisplayMetrics displayMetrics, int apiLevel) {
    Locale locale = getLocale(configuration, apiLevel);

    String language = locale == null ? "" : locale.getLanguage();
    if (language.isEmpty()) {
      language = "en";

      String country = locale == null ? "" : locale.getCountry();
      if (country.isEmpty()) {
        country = "us";
      }

      locale = new Locale(language, country);
      setLocale(apiLevel, configuration, locale);
    }

    if (apiLevel <= ConfigDescription.SDK_JELLY_BEAN &&
        getScreenLayoutLayoutDir(configuration) == Configuration.SCREENLAYOUT_LAYOUTDIR_UNDEFINED) {
      setScreenLayoutLayoutDir(configuration, Configuration.SCREENLAYOUT_LAYOUTDIR_LTR);
    }

    ScreenSize requestedScreenSize = getScreenSize(configuration);
    if (requestedScreenSize == null) {
      requestedScreenSize = DEFAULT_SCREEN_SIZE;
    }

    if (configuration.orientation == Configuration.ORIENTATION_UNDEFINED
        && configuration.screenWidthDp != 0 && configuration.screenHeightDp != 0) {
      configuration.orientation = (configuration.screenWidthDp > configuration.screenHeightDp)
          ? Configuration.ORIENTATION_LANDSCAPE
          : Configuration.ORIENTATION_PORTRAIT;
    }

    if (configuration.screenWidthDp == 0) {
      configuration.screenWidthDp = requestedScreenSize.width;
    }

    if (configuration.screenHeightDp == 0) {
      configuration.screenHeightDp = requestedScreenSize.height;

      if ((configuration.screenLayout & Configuration.SCREENLAYOUT_LONG_MASK)
          == Configuration.SCREENLAYOUT_LONG_YES) {
        configuration.screenHeightDp = (int) (configuration.screenHeightDp * 1.25f);
      }
    }

    int lesserDimenPx = Math.min(configuration.screenWidthDp, configuration.screenHeightDp);
    int greaterDimenPx = Math.max(configuration.screenWidthDp, configuration.screenHeightDp);

    if (configuration.smallestScreenWidthDp == 0) {
      configuration.smallestScreenWidthDp = lesserDimenPx;
    }

    if (getScreenLayoutSize(configuration) == Configuration.SCREENLAYOUT_SIZE_UNDEFINED) {
      ScreenSize screenSize =
          ScreenSize.match(configuration.screenWidthDp, configuration.screenHeightDp);
      setScreenLayoutSize(configuration, screenSize.configValue);
    }

    if (getScreenLayoutLong(configuration) == Configuration.SCREENLAYOUT_LONG_UNDEFINED) {
      setScreenLayoutLong(configuration,
          ((float) greaterDimenPx) / lesserDimenPx >= 1.75
              ? Configuration.SCREENLAYOUT_LONG_YES
              : Configuration.SCREENLAYOUT_LONG_NO);
    }

    if (getScreenLayoutRound(configuration) == Configuration.SCREENLAYOUT_ROUND_UNDEFINED) {
      setScreenLayoutRound(configuration, Configuration.SCREENLAYOUT_ROUND_NO);
    }

    if (configuration.orientation == Configuration.ORIENTATION_UNDEFINED) {
      configuration.orientation = configuration.screenWidthDp > configuration.screenHeightDp
          ? Configuration.ORIENTATION_LANDSCAPE
          : Configuration.ORIENTATION_PORTRAIT;
    } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        && configuration.screenWidthDp > configuration.screenHeightDp) {
      swapXY(configuration);
    } else if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        && configuration.screenWidthDp < configuration.screenHeightDp) {
      swapXY(configuration);
    }

    if (getUiModeType(configuration) == Configuration.UI_MODE_TYPE_UNDEFINED) {
      setUiModeType(configuration, Configuration.UI_MODE_TYPE_NORMAL);
    }

    if (getUiModeNight(configuration) == Configuration.UI_MODE_NIGHT_UNDEFINED) {
      setUiModeNight(configuration, Configuration.UI_MODE_NIGHT_NO);
    }

    switch (displayMetrics.densityDpi) {
      case ResTable_config.DENSITY_DPI_ANY:
        throw new IllegalArgumentException("'anydpi' isn't actually a dpi");
      case ResTable_config.DENSITY_DPI_NONE:
        throw new IllegalArgumentException("'nodpi' isn't actually a dpi");
      case ResTable_config.DENSITY_DPI_UNDEFINED:
        // DisplayMetrics.DENSITY_DEFAULT is mdpi
        setDensity(DEFAULT_DENSITY, apiLevel, configuration, displayMetrics);
    }

    if (configuration.touchscreen == Configuration.TOUCHSCREEN_UNDEFINED) {
      configuration.touchscreen = Configuration.TOUCHSCREEN_FINGER;
    }

    if (configuration.keyboardHidden == Configuration.KEYBOARDHIDDEN_UNDEFINED) {
      configuration.keyboardHidden = Configuration.KEYBOARDHIDDEN_SOFT;
    }

    if (configuration.keyboard == Configuration.KEYBOARD_UNDEFINED) {
      configuration.keyboard = Configuration.KEYBOARD_NOKEYS;
    }

    if (configuration.navigationHidden == Configuration.NAVIGATIONHIDDEN_UNDEFINED) {
      configuration.navigationHidden = Configuration.NAVIGATIONHIDDEN_YES;
    }

    if (configuration.navigation == Configuration.NAVIGATION_UNDEFINED) {
      configuration.navigation = Configuration.NAVIGATION_NONAV;
    }

    if (apiLevel >= VERSION_CODES.O) {
      if (getColorModeGamut(configuration) == Configuration.COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED) {
        setColorModeGamut(configuration, Configuration.COLOR_MODE_WIDE_COLOR_GAMUT_NO);
      }

      if (getColorModeHdr(configuration) == Configuration.COLOR_MODE_HDR_UNDEFINED) {
        setColorModeHdr(configuration, Configuration.COLOR_MODE_HDR_NO);
      }
    }
  }