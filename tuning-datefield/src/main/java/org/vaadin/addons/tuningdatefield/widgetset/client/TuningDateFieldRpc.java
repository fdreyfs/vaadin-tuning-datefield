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

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

public interface TuningDateFieldRpc extends ServerRpc {

    /**
     * Called when a user clicked on a given cell item in the calendar.
     * 
     * @param relativeDateIndex
     *            (dayOfMonth, monthOfYear or year depending on the resolution) the day of relativeDateIndex
     * @param mouseDetails
     *            the mouse event details on click
     */
    public void calendarItemClicked(Integer relativeDateIndex, MouseEventDetails mouseDetails);

    /**
     * Called when the date text is changed by the user
     * 
     * @param dateText
     *            the date text
     */
    public void dateTextChanged(String dateText);

    /**
     * Called when the calendar is open
     */
    public void onCalendarOpen();

    /**
     * Called when the calendar is closed
     */
    public void onCalendarClosed();

    /**
     * Called when a user clicked on the previousMonth control in the dayPicker calendar.
     */
    public void previousControlClicked();

    /**
     * Called when a user clicked on the nextMonth control in the dayPicker calendar.
     */
    public void nextControlClicked();

    /**
     * Called when a user clicked on the monthPicker control in the dayPicker calendar.
     */
    public void resolutionControlClicked();

}
