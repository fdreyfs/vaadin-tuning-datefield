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

import org.vaadin.addons.tuningdatefield.widgetset.client.ui.TuningDateFieldWidget;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public class DayCalendarTable extends AbstractCalendarTable {

    private static final int WEEKDAY_NAMES_ROW = 1;

    public DayCalendarTable(TuningDateFieldWidget tuningDateField, String resolutionControlText, CalendarItem[] calendarItems,
            boolean controlsEnabled) {
        super(tuningDateField, resolutionControlText, calendarItems, controlsEnabled);
    }

    protected int getFirstCellItemsRow() {
        return 2;
    }

    @Override
    protected int getNumberOfColumns() {
        return 7;
    }

    @Override
    protected String getCellItemPrimaryStylename() {
        return "day";
    }

    @Override
    protected void renderHeader() {
        renderControls();
        renderWeekHeaderNames();
    }

    private void renderWeekHeaderNames() {
        getRowFormatter().setStylePrimaryName(WEEKDAY_NAMES_ROW, "daynames");
        for (int i = 0; i < getNumberOfColumns(); i++) {
            setText(WEEKDAY_NAMES_ROW, i, tuningDateField.getWeekHeaderNames()[i]);
            getCellFormatter().setAlignment(WEEKDAY_NAMES_ROW, i, HasHorizontalAlignment.ALIGN_CENTER,
                    HasVerticalAlignment.ALIGN_MIDDLE);
        }
    }

}
