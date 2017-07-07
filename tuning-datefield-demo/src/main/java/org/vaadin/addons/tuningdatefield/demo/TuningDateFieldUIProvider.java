package org.vaadin.addons.tuningdatefield.demo;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

public class TuningDateFieldUIProvider extends UIProvider {

    private static final long serialVersionUID = 7453071516672841797L;

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        String theme = (String) event.getRequest().getAttribute("theme");
        return TuningDateFieldValoDemoUI.class;
    }

}
