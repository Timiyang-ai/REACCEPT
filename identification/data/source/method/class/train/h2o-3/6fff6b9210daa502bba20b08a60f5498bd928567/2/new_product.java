public double[] convert(RowData data, double[] rawData) throws PredictException {  

    // TODO: refactor
    boolean isImage = _m instanceof DeepwaterMojoModel && ((DeepwaterMojoModel) _m)._problem_type.equals("image");
    boolean isText  = _m instanceof DeepwaterMojoModel && ((DeepwaterMojoModel) _m)._problem_type.equals("text");

    for (String dataColumnName : data.keySet()) {
      Integer index = _modelColumnNameToIndexMap.get(dataColumnName);

      // Skip column names that are not known.
      // Skip the "response" column which should not be included in `rawData`
      if (index == null || index >= rawData.length) {
        continue;
      }

      BufferedImage img = null;
      String[] domainValues = _m.getDomainValues(index);

      if (domainValues == null) {
        // Column is either numeric or a string (for images or text)
        double value = Double.NaN;
        Object o = data.get(dataColumnName);
        if (o instanceof String) {
          String s = ((String) o).trim();
          // Url to an image given
          if (isImage) {
            boolean isURL = s.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
            try {
              img = isURL? ImageIO.read(new URL(s)) : ImageIO.read(new File(s));
            }
            catch (IOException e) {
              throw new PredictException("Couldn't read image from " + s);
            }
          } else if (isText) {
            // TODO: use model-specific vectorization of text
            throw new PredictException("MOJO scoring for text classification is not yet implemented.");
          }
          else {
            // numeric
            try {
              value = Double.parseDouble(s);
            } catch(NumberFormatException nfe) {
              if (!_convertInvalidNumbersToNa)
                throw new PredictNumberFormatException("Unable to parse value: " + s + ", from column: "+ dataColumnName + ", as Double; " + nfe.getMessage());
            }
          }
        } else if (o instanceof Double) {
          value = (Double) o;
        } else if (o instanceof byte[] && isImage) {
          // Read the image from raw bytes
          InputStream is = new ByteArrayInputStream((byte[]) o);
          try {
            img = ImageIO.read(is);
          } catch (IOException e) {
            throw new PredictException("Couldn't interpret raw bytes as an image.");
          }
        } else {
          throw new PredictUnknownTypeException(
                  "Unexpected object type " + o.getClass().getName() + " for numeric column " + dataColumnName);
        }

        if (isImage && img != null) {
          DeepwaterMojoModel dwm = (DeepwaterMojoModel) _m;
          int W = dwm._width;
          int H = dwm._height;
          int C = dwm._channels;
          float[] _destData = new float[W * H * C];
          try {
            GenModel.img2pixels(img, W, H, C, _destData, 0, dwm._meanImageData);
          } catch (IOException e) {
            e.printStackTrace();
            throw new PredictException("Couldn't vectorize image.");
          }
          rawData = new double[_destData.length];
          for (int i = 0; i < rawData.length; ++i)
            rawData[i] = _destData[i];
          return rawData;
        }

        if (Double.isNaN(value)) {
          // If this point is reached, the original value remains NaN.
          _errorConsumer.dataTransformError(dataColumnName, o, "Given non-categorical value is unparseable, treating as NaN.");
        }
        rawData[index] = value;
      }
      else {
        // Column has categorical value.
        Object o = data.get(dataColumnName);
        double value;
        if (o instanceof String) {
          String levelName = (String) o;
          HashMap<String, Integer> columnDomainMap = _domainMap.get(index);
          Integer levelIndex = columnDomainMap.get(levelName);
          if (levelIndex == null) {
            levelIndex = columnDomainMap.get(dataColumnName + "." + levelName);
          }
          if (levelIndex == null) {
            if (_convertUnknownCategoricalLevelsToNa) {
              value = Double.NaN;
              _errorConsumer.unseenCategorical(dataColumnName, o, "Previously unseen categorical level detected, marking as NaN.");
            } else {
              _errorConsumer.dataTransformError(dataColumnName, o, "Unknown categorical level detected.");
              throw new PredictUnknownCategoricalLevelException("Unknown categorical level (" + dataColumnName + "," + levelName + ")", dataColumnName, levelName);
            }
          }
          else {
            value = levelIndex;
          }
        } else if (o instanceof Double && Double.isNaN((double)o)) {
          _errorConsumer.dataTransformError(dataColumnName, o, "Missing factor value detected, setting to NaN");
          value = (double)o; //Missing factor is the only Double value allowed
        } else {
          _errorConsumer.dataTransformError(dataColumnName, o, "Unknown categorical variable type.");
          throw new PredictUnknownTypeException(
                  "Unexpected object type " + o.getClass().getName() + " for categorical column " + dataColumnName);
        }
        rawData[index] = value;
      }
    }
    return rawData;
  }