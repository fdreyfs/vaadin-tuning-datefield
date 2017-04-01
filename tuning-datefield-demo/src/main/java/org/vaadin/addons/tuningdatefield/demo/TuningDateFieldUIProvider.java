package org.vaadin.addons.tuningdatefield.demo;

import com.google.common.base.Strings;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

public class TuningDateFieldUIProvider extends UIProvider {

    private static final long serialVersionUID = 7453071516672841797L;

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        String theme = (String) event.getRequest().getAttribute("theme");

        Class<? extends UI> UIclass = null;
        if (Strings.isNullOrEmpty(theme) || "demo_reindeer".equals(theme)) {
            UIclass = TuningDateFieldReindeerDemoUI.class;
        } else if ("demo_runo".equals(theme)) {
            UIclass = TuningDateFieldRunoDemoUI.class;
        }

        return UIclass;
    }

}
