public static List<String> buildTripOptions(Context c, boolean isRouteFavorite, boolean hasUrl,
            boolean isReminderVisible) {
        ArrayList<String> list = new ArrayList<>();
        if (!isRouteFavorite) {
            list.add(c.getString(R.string.bus_options_menu_add_star));
        } else {
            list.add(c.getString(R.string.bus_options_menu_remove_star));
        }

        list.add(c.getString(R.string.bus_options_menu_show_route_on_map));
        list.add(c.getString(R.string.bus_options_menu_show_trip_details));

        if (!isReminderVisible) {
            list.add(c.getString(R.string.bus_options_menu_set_reminder));
        } else {
            list.add(c.getString(R.string.bus_options_menu_edit_reminder));
        }

        list.add(c.getString(R.string.bus_options_menu_show_only_this_route));

        if (hasUrl) {
            list.add(c.getString(R.string.bus_options_menu_show_route_schedule));
        }

        list.add(c.getString(R.string.bus_options_menu_report_trip_problem));

        ObaRegion currentRegion = Application.get().getCurrentRegion();
        if (currentRegion != null && EmbeddedSocialUtils.isSocialEnabled()) {
            list.add(c.getString(R.string.join_discussion));
        }

        return list;
    }