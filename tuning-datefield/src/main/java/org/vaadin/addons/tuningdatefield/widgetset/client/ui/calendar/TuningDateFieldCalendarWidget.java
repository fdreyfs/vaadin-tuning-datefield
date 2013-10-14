/*
 * Copyright (C) 2013 Frederic Dreyfus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar;

import org.vaadin.addons.tuningdatefield.widgetset.client.ui.TuningDateFieldBundle;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.TuningDateFieldWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ui.VCalendarPanel.FocusOutListener;
import com.vaadin.client.ui.VCalendarPanel.SubmitListener;

public class TuningDateFieldCalendarWidget extends SimplePanel {

    public static final String CLASSNAME = "tuning-datefield-calendar";

    private TuningDateFieldWidget parentField;

    private FocusOutListener focusOutListener;
    private SubmitListener submitListener;

    private Widget loadingWidget;
    private Widget currentCalendarTable;

    public TuningDateFieldCalendarWidget() {
        setStyleName(CLASSNAME);
        TuningDateFieldBundle tuningDateFieldBundle = GWT.create(TuningDateFieldBundle.class);
        loadingWidget = new Image(tuningDateFieldBundle.getLoadingIndicator());
    }

    public void redraw() {

        if (currentCalendarTable != null) {
            remove(currentCalendarTable);
        }
        setWidget(loadingWidget);
        if (parentField.isCalendarOpen()) {
            switch (parentField.getCalendarResolution()) {
            case MONTH:
                currentCalendarTable = new MonthCalendarTable(parentField, parentField.getCalendarResolutionText(),
                        parentField.getCalendarItems(), parentField.isControlsEnabled());
                break;
            case YEAR:
                currentCalendarTable = new YearCalendarTable(parentField, parentField.getCalendarResolutionText(),
                        parentField.getCalendarItems(), parentField.isControlsEnabled());
                break;
            default:
                currentCalendarTable = new DayCalendarTable(parentField, parentField.getCalendarResolutionText(),
                        parentField.getCalendarItems(), parentField.isControlsEnabled());
                break;
            }

            setWidget(currentCalendarTable);
        }

    }

    /**
     * @return the parentField
     */
    public TuningDateFieldWidget getParentField() {
        return parentField;
    }

    /**
     * @param parentField
     *            the parentField to set
     */
    public void setParentField(TuningDateFieldWidget parentField) {
        this.parentField = parentField;
    }

    /**
     * @return the focusOutListener
     */
    public FocusOutListener getFocusOutListener() {
        return focusOutListener;
    }

    /**
     * @param focusOutListener
     *            the focusOutListener to set
     */
    public void setFocusOutListener(FocusOutListener focusOutListener) {
        this.focusOutListener = focusOutListener;
    }

    /**
     * @return the submitListener
     */
    public SubmitListener getSubmitListener() {
        return submitListener;
    }

    /**
     * @param submitListener
     *            the submitListener to set
     */
    public void setSubmitListener(SubmitListener submitListener) {
        this.submitListener = submitListener;
    }

}
