private void i18nJs(final Handlebars handlebars, final List<CharSequence> extras,
      final URL[] classpath) {
    handlebars.registerHelper(I18nHelper.i18nJs.name(), new Helper<String>() {
      @Override
      public CharSequence apply(final String context, final Options options) throws IOException {
        Map<String, Object> hash = options.hash;
        hash.put("wrap", false);
        if (classpath.length > 0) {
          hash.put("classLoader",
              new URLClassLoader(classpath, getClass().getClassLoader()));
        }

        Options opts = new Options.Builder(options.handlebars, options.tagType, options.context,
            options.fn)
            .setHash(hash)
            .setInverse(options.inverse)
            .setParams(options.params)
            .build();

        StringBuilder output = new StringBuilder();
        output.append("// i18nJs output:\n");
        output.append("// register an empty i18nJs helper:\n");
        output.append(registerHelper(I18nHelper.i18nJs.name(), "if (arguments.length > 1) {\n"
            + "  I18n.locale=arguments[0];\n"
            + "}\n"
            + "return '';", "arguments"));
        output.append("// redirect i18n helper to i18n.js:\n");
        output.append(registerHelper(I18nHelper.i18n.name(), "var key = arguments[0],\n"
            + "  i18nOpts = {},\n"
            + "  len = arguments.length - 1,"
            + "  options = arguments[len];\n"
            + "for(var i = 1; i < len; i++) {\n"
            + "  i18nOpts['arg' + (i - 1)] = arguments[i];\n"
            + "}\n"
            + "i18nOpts.locale = options.hash.locale;\n"
            + "return I18n.t(key, i18nOpts);"));
        output.append(I18nHelper.i18nJs.apply(context, opts));
        extras.add(output);
        return null;
      }

      @Override
      public String toString() {
        return I18nHelper.i18nJs.name() + "-maven-plugin";
      }
    });
  }