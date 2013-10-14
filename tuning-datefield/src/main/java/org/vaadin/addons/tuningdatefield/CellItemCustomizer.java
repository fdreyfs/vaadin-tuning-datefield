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

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar.CalendarResolution;

/**
 * An interface that allows to customize the calendar cell items of the {@link TuningDateField}.<br />
 * As there are 3 different calendars with their respective resolutions (day, month, year) the customizer can be applied
 * to all these calendars.<br />
 * Consider using {@link CellItemCustomizerAdapter} which defines default implementations for all 3 calendar
 * resolutions.
 * 
 * @author Frederic.Dreyfus
 * 
 * @see CellItemCustomizerAdapter
 */
public interface CellItemCustomizer {

    /**
     * Returns the style of the cell from its {@link LocalDate} representation in the {@link CalendarResolution#DAY}
     * calendar resolution.
     * 
     * @param date
     *            the date representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the style of the cell from its date representation.
     */
    public String getStyle(LocalDate date, TuningDateField tuningDateField);

    /**
     * Returns the tooltip of the cell from its {@link LocalDate} representation in the {@link CalendarResolution#DAY}
     * calendar resolution.
     * 
     * @param date
     *            the date representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the tooltip of the cell from its date representation.
     */
    public String getTooltip(LocalDate date, TuningDateField tuningDateField);

    /**
     * Returns <code>true</code> if the cell is enabled from its {@link LocalDate} representation in the
     * {@link CalendarResolution#DAY} calendar resolution.
     * 
     * @param date
     *            the date representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the <code>true</code> if the cell is enabled, else returns <code>false</code>
     */
    public boolean isEnabled(LocalDate date, TuningDateField tuningDateField);

    /**
     * Returns the style of the cell from its {@link YearMonth} representation in the {@link CalendarResolution#MONTH}
     * calendar resolution.
     * 
     * @param yearMonth
     *            the yearMonth representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the style of the cell from its yearMonth representation.
     */
    public String getStyle(YearMonth yearMonth, TuningDateField tuningDateField);

    /**
     * Returns the tooltip of the cell from its {@link YearMonth} representation in the {@link CalendarResolution#MONTH}
     * calendar resolution.
     * 
     * @param yearMonth
     *            the yearMonth representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the tooltip of the cell from its yearMonth representation.
     */
    public String getTooltip(YearMonth yearMonth, TuningDateField tuningDateField);

    /**
     * Returns <code>true</code> if the cell is enabled from its {@link YearMonth} representation in the
     * {@link CalendarResolution#MONTH} calendar resolution.
     * 
     * @param yearMonth
     *            yearMonth date representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the <code>true</code> if the cell is enabled, else returns <code>false</code>
     */
    public boolean isEnabled(YearMonth yearMonth, TuningDateField tuningDateField);

    /**
     * Returns the style of the cell from its year representation in the {@link CalendarResolution#YEAR} calendar
     * resolution.
     * 
     * @param year
     *            the year representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the style of the cell from its yaer representation.
     */
    public String getStyle(int year, TuningDateField tuningDateField);

    /**
     * Returns the tooltip of the cell from its year representation in the {@link CalendarResolution#YEAR} calendar
     * resolution.
     * 
     * @param year
     *            the year representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the tooltip of the cell from its yaer representation.
     */
    public String getTooltip(int year, TuningDateField tuningDateField);

    /**
     * Returns <code>true</code> if the cell is enabled from its year representation in the
     * {@link CalendarResolution#YEAR} calendar resolution.
     * 
     * @param year
     *            year date representing the cell
     * @param tuningDateField
     *            the {@link TuningDateField}
     * @return the <code>true</code> if the cell is enabled, else returns <code>false</code>
     */
    public boolean isEnabled(int year, TuningDateField tuningDateField);

}
