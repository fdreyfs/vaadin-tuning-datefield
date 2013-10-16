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

package org.vaadin.addons.tuningdatefield.event;

import org.joda.time.YearMonth;

import com.vaadin.ui.Component;

/**
 * Event dispatched when the calendar is open.
 * 
 * @author Frederic.Dreyfus
 * 
 */
public class CalendarOpenEvent extends Component.Event {

    private static final long serialVersionUID = -5039867014726965557L;

    /**
     * The yearMonth displayed in the calendar.
     */
    private YearMonth yearMonth;

    public CalendarOpenEvent(Component source, YearMonth yearMonth) {
        super(source);
        this.yearMonth = yearMonth;
    }

    /**
     * @return the yearMonth
     */
    public YearMonth getYearMonth() {
        return yearMonth;
    }

    /**
     * @param yearMonth
     *            the yearMonth to set
     */
    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

}
