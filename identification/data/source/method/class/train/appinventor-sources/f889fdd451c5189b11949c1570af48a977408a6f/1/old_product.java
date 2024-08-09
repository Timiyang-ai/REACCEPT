@DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING)
  @SimpleProperty
  public void PointsFromString(String points) {
    final String functionName = "PointsFromString";
    try {
      List<GeoPoint> geopoints = new ArrayList<GeoPoint>();
      JSONArray array = new JSONArray(points);
      if (array.length() < 2) {
        // Need at least two points
        throw new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_POINTS, array.length());
      }
      int length = array.length();
      for (int i = 0; i < length; ++i) {
        JSONArray point = array.optJSONArray(i);
        if (point == null) {
          throw new DispatchableError(ErrorMessages.ERROR_EXPECTED_ARRAY_AT_INDEX, i,
              array.get(i).toString());
        } else if (point.length() < 2) {
          throw new DispatchableError(ErrorMessages.ERROR_LINESTRING_TOO_FEW_FIELDS, i,
              points.length());
        }
        double latitude = point.optDouble(0, Double.NaN);
        double longitude = point.optDouble(1, Double.NaN);
        if (!isValidLatitude(latitude)) {
          throw new DispatchableError(ErrorMessages.ERROR_INVALID_LATITUDE_IN_POINT_AT_INDEX,
              i, array.get(0).toString());
        } else if (!isValidLongitude(longitude)) {
          throw new DispatchableError(ErrorMessages.ERROR_INVALID_LONGITUDE_IN_POINT_AT_INDEX,
              i, array.get(1).toString());
        }
        geopoints.add(new GeoPoint(latitude, longitude));
      }
      this.points = geopoints;
      clearGeometry();
      map.getController().updateFeaturePosition(this);
    } catch(JSONException e) {
      Log.e(TAG, "Malformed string to LineString.PointsFromString", e);
      container.$form().dispatchErrorOccurredEvent(this, functionName, ErrorMessages.ERROR_LINESTRING_PARSE_ERROR, e.getMessage());
    } catch(DispatchableError e) {
      container.$form().dispatchErrorOccurredEvent(this, functionName, e.getErrorCode(), e.getArguments());
    }
  }