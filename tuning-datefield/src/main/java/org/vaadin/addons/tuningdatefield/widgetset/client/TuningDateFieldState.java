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

package org.vaadin.addons.tuningdatefield.widgetset.client;

import org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar.CalendarItem;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar.CalendarResolution;

import com.vaadin.shared.AbstractFieldState;

public class TuningDateFieldState extends AbstractFieldState {

    private static final long serialVersionUID = 3039435561807445311L;

    /**
     * The date displayed in the textBox
     */
    private String displayedDateText;

    private boolean dateTextReadOnly;

    // //////////////////////
    // Data for calendar
    // //////////////////////
    private boolean calendarOpen;

    private CalendarResolution calendarResolution;

    private String calendarResolutionText;

    private boolean controlsEnabled;

    private CalendarItem[] calendarItems;

    // Only for day calendar
    private String[] weekHeaderNames;
    
    // Allow to open calendar on text field focus
    private boolean openCalendarOnFocusEnabled;

    public TuningDateFieldState() {

    }

    /**
     * @return the displayedDateText
     */
    public String getDisplayedDateText() {
        return displayedDateText;
    }

    /**
     * @param displayedDateText the displayedDateText to set
     */
    public void setDisplayedDateText(String displayedDateText) {
        this.displayedDateText = displayedDateText;
    }

    /**
     * @return the dateTextReadOnly
     */
    public boolean isDateTextReadOnly() {
        return dateTextReadOnly;
    }

    /**
     * @param dateTextReadOnly the dateTextReadOnly to set
     */
    public void setDateTextReadOnly(boolean dateTextReadOnly) {
        this.dateTextReadOnly = dateTextReadOnly;
    }

    /**
     * @return the calendarOpen
     */
    public boolean isCalendarOpen() {
        return calendarOpen;
    }

    /**
     * @param calendarOpen the calendarOpen to set
     */
    public void setCalendarOpen(boolean calendarOpen) {
        this.calendarOpen = calendarOpen;
    }

    /**
     * @return the calendarResolution
     */
    public CalendarResolution getCalendarResolution() {
        return calendarResolution;
    }

    /**
     * @param calendarResolution the calendarResolution to set
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
     * @param calendarResolutionText the calendarResolutionText to set
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
     * @param controlsEnabled the controlsEnabled to set
     */
    public void setControlsEnabled(boolean controlsEnabled) {
        this.controlsEnabled = controlsEnabled;
    }

    /**
     * @return the calendarItems
     */
    public CalendarItem[] getCalendarItems() {
        return calendarItems;
    }

    /**
     * @param calendarItems the calendarItems to set
     */
    public void setCalendarItems(CalendarItem[] calendarItems) {
        this.calendarItems = calendarItems;
    }

    /**
     * @return the weekHeaderNames
     */
    public String[] getWeekHeaderNames() {
        return weekHeaderNames;
    }

    /**
     * @param weekHeaderNames the weekHeaderNames to set
     */
    public void setWeekHeaderNames(String[] weekHeaderNames) {
        this.weekHeaderNames = weekHeaderNames;
    }

    public boolean isOpenCalendarOnFocusEnabled() {
        return openCalendarOnFocusEnabled;
    }

    public void setOpenCalendarOnFocusEnabled(boolean openCalendarOnFocusEnabled) {
        this.openCalendarOnFocusEnabled = openCalendarOnFocusEnabled;
    }

    

}
