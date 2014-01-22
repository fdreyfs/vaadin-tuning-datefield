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


package org.vaadin.addons.tuningdatefield;

import org.joda.time.YearMonth;
import org.vaadin.addons.tuningdatefield.widgetset.client.InlineTuningDateFieldRpc;
import org.vaadin.addons.tuningdatefield.widgetset.client.TuningDateFieldState;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar.CalendarResolution;

import com.vaadin.shared.MouseEventDetails;

/**
 * An inline version of the {@link TuningDateField} which displays the calendar.
 * 
 * @author Frederic.Dreyfus
 * 
 * @see TuningDateField
 */
// FIXME this needs some refactoring...
public class InlineTuningDateField extends TuningDateField {

    private static final long serialVersionUID = 4586853667789785236L;
    
    public InlineTuningDateField() {
        calendarOpen = true;
    }

    protected void registerRpc() {
        registerRpc(new InlineTuningDateFieldRpc() {

            private static final long serialVersionUID = -6765204929172002847L;

            @Override
            public void calendarItemClicked(Integer relativeDateIndex, MouseEventDetails mouseDetails) {
                onCalendarItemClicked(relativeDateIndex, mouseDetails);
            }

            @Override
            public void dateTextChanged(String dateText) {
                setValue(dateText);
            }

            @Override
            public void previousControlClicked() {
                if (controlsEnabled) {
                    goToPreviousCalendarPage();
                } else {
                    // wtf ? should never happen
                }
            }

            @Override
            public void nextControlClicked() {
                if (controlsEnabled) {
                    goToNextCalendarPage();
                } else {
                    // wtf ? should never happen
                }
            }

            @Override
            public void resolutionControlClicked() {
                if (controlsEnabled) {
                    swithToHigherCalendarResolution();
                } else {
                    // wtf ? should never happen
                }
            }

            @Override
            public void onCalendarOpen() {
                // not implemented

            }

            @Override
            public void onCalendarClosed() {
                // not implemented

            }

        });
    }

    @Override
    public TuningDateFieldState getState() {
        return (TuningDateFieldState) super.getState();
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);

        getState().setControlsEnabled(isControlsEnabled());
        getState().setCalendarResolution(calendarResolution);

        if (calendarResolution.equals(CalendarResolution.DAY)) {
            YearMonth yearMonthDisplayed = getYearMonthDisplayed();
            String displayedMonthText = monthTexts[yearMonthDisplayed.getMonthOfYear() - 1];
            getState().setCalendarResolutionText(displayedMonthText + " " + yearMonthDisplayed.getYear());
            getState().setWeekHeaderNames(weekDayNames);
            getState().setCalendarItems(buildDayItems());
        } else if (calendarResolution.equals(CalendarResolution.MONTH)) {
            getState().setCalendarItems(buildMonthItems());
            getState().setCalendarResolutionText(Integer.toString(yearMonthDisplayed.getYear()));
        } else if (calendarResolution.equals(CalendarResolution.YEAR)) {
            getState().setCalendarItems(buildYearItems());
            getState().setCalendarResolutionText(getCalendarFirstYear() + " - " + getCalendarLastYear());
        }
    }

}
