public static <T> Object selectableConvert(
      Type<T> type, Object x, Object what, LabelConversionContext context)
      throws ConversionException {
    if (x instanceof com.google.devtools.build.lib.syntax.SelectorList) {
      return new SelectorList<T>(
          ((com.google.devtools.build.lib.syntax.SelectorList) x).getElements(),
          what, context, type);
    } else {
      return type.convert(x, what, context);
    }
  }