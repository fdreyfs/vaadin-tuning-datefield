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
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarAttachedEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarAttachedHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarItemClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarItemClickHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.NextControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.NextControlClickHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.PreviousControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.PreviousControlClickHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.ResolutionControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.ResolutionControlClickHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ui.VCalendarPanel.FocusOutListener;
import com.vaadin.client.ui.VCalendarPanel.SubmitListener;

public class TuningDateFieldCalendarWidget extends SimplePanel {

    public static final String CLASSNAME = "tuning-datefield-calendar";

    // //////////////////////
    // Data for calendar
    // //////////////////////
    private CalendarResolution calendarResolution;
    private String calendarResolutionText;

    private boolean controlsEnabled;

    // For Day calendar resolutions
    private String[] weekHeaderNames;

    private CalendarItem[] calendarItems;

    private FocusOutListener focusOutListener;
    private SubmitListener submitListener;

    private Widget loadingWidget;
    private Widget currentCalendarTable;

    public TuningDateFieldCalendarWidget() {
        setStyleName(CLASSNAME);
        TuningDateFieldBundle tuningDateFieldBundle = GWT.create(TuningDateFieldBundle.class);
        loadingWidget = new Image(tuningDateFieldBundle.getLoadingIndicator());
    }

    public void redraw(boolean calendarOpen) {
        if (currentCalendarTable != null) {
            remove(currentCalendarTable);
        }
        setWidget(loadingWidget);
        if (calendarOpen) {
            switch (calendarResolution) {
            case MONTH:
                currentCalendarTable = new MonthCalendarTable(this, calendarResolutionText, calendarItems,
                        controlsEnabled);
                break;
            case YEAR:
                currentCalendarTable = new YearCalendarTable(this, calendarResolutionText, calendarItems,
                        controlsEnabled);
                break;
            default:
                currentCalendarTable = new DayCalendarTable(this, calendarResolutionText, calendarItems,
                        controlsEnabled);
                break;
            }
            setWidget(currentCalendarTable);
            // We notify the calendar is attached so that we update
            // the popup position
            fireEvent(new CalendarAttachedEvent());
        }
    }
    
    public HandlerRegistration addCalendarItemClickHandler(CalendarItemClickHandler calendarItemClickHandler) {
        return addHandler(calendarItemClickHandler, CalendarItemClickEvent.getType());
    }

    public HandlerRegistration addPreviousControlClickHandler(PreviousControlClickHandler previousControlClickHandler) {
        return addHandler(previousControlClickHandler, PreviousControlClickEvent.getType());
    }

    public HandlerRegistration addNextControlClickHandler(NextControlClickHandler nextControlClickHandler) {
        return addHandler(nextControlClickHandler, NextControlClickEvent.getType());
    }

    public HandlerRegistration addResolutionControlClickHandler(
            ResolutionControlClickHandler resolutionControlClickHandler) {
        return addHandler(resolutionControlClickHandler, ResolutionControlClickEvent.getType());
    }

    public HandlerRegistration addCalendarAttachedHandler(CalendarAttachedHandler calendarAttachedHandler) {
        return addHandler(calendarAttachedHandler, CalendarAttachedEvent.getType());
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

    /**
     * @return the calendarResolution
     */
    public CalendarResolution getCalendarResolution() {
        return calendarResolution;
    }

    /**
     * @param calendarResolution
     *            the calendarResolution to set
     */
    public void setCalendarResolution(CalendarResolution calendarResolution) {
        this.calendarResolution = calendarResolution;
    }

    /**
     * @return the calendarResolutionText
     */
    public String getCalendarResolutionText() {
        return calendarResolutionText;
    }

    /**
     * @param calendarResolutionText
     *            the calendarResolutionText to set
     */
    public void setCalendarResolutionText(String calendarResolutionText) {
        this.calendarResolutionText = calendarResolutionText;
    }

    /**
     * @return the controlsEnabled
     */
    public boolean isControlsEnabled() {
        return controlsEnabled;
    }

    /**
     * @param controlsEnabled
     *            the controlsEnabled to set
     */
    public void setControlsEnabled(boolean controlsEnabled) {
        this.controlsEnabled = controlsEnabled;
    }

    /**
     * @return the weekHeaderNames
     */
    public String[] getWeekHeaderNames() {
        return weekHeaderNames;
    }

    /**
     * @param weekHeaderNames
     *            the weekHeaderNames to set
     */
    public void setWeekHeaderNames(String[] weekHeaderNames) {
        this.weekHeaderNames = weekHeaderNames;
    }

    /**
     * @return the calendarItems
     */
    public CalendarItem[] getCalendarItems() {
        return calendarItems;
    }

    /**
     * @param calendarItems
     *            the calendarItems to set
     */
    public void setCalendarItems(CalendarItem[] calendarItems) {
        this.calendarItems = calendarItems;
    }

    /**
     * @return the loadingWidget
     */
    public Widget getLoadingWidget() {
        return loadingWidget;
    }

    /**
     * @param loadingWidget
     *            the loadingWidget to set
     */
    public void setLoadingWidget(Widget loadingWidget) {
        this.loadingWidget = loadingWidget;
    }

    /**
     * @return the currentCalendarTable
     */
    public Widget getCurrentCalendarTable() {
        return currentCalendarTable;
    }

    /**
     * @param currentCalendarTable
     *            the currentCalendarTable to set
     */
    public void setCurrentCalendarTable(Widget currentCalendarTable) {
        this.currentCalendarTable = currentCalendarTable;
    }

}
