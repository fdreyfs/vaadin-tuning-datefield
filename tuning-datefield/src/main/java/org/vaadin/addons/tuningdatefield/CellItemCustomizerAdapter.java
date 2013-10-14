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

/**
 * Default implementation of {@link CellItemCustomizer} that enables all cells, does not return any tooltip or style.
 * 
 * @author Frederic.Dreyfus
 * 
 */
public class CellItemCustomizerAdapter implements CellItemCustomizer {

    @Override
    public String getStyle(LocalDate date, TuningDateField tuningDateField) {
        return null;
    }

    @Override
    public String getTooltip(LocalDate date, TuningDateField tuningDateField) {
        return null;
    }

    @Override
    public boolean isEnabled(LocalDate date, TuningDateField tuningDateField) {
        return true;
    }

    @Override
    public String getStyle(YearMonth yearMonth, TuningDateField tuningDateField) {
        return null;
    }

    @Override
    public String getTooltip(YearMonth yearMonth, TuningDateField tuningDateField) {
        return null;
    }

    @Override
    public boolean isEnabled(YearMonth yearMonth, TuningDateField tuningDateField) {
        return true;
    }

    @Override
    public String getStyle(int year, TuningDateField tuningDateField) {
        return null;
    }

    @Override
    public String getTooltip(int year, TuningDateField tuningDateField) {
        return null;
    }

    @Override
    public boolean isEnabled(int year, TuningDateField tuningDateField) {
        return true;
    }

}
