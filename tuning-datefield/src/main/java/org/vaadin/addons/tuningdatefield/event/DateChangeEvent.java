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

import java.time.LocalDate;

import com.vaadin.ui.Component;

/**
 * Event dispatched when the date changed in the calendar or in the text field.
 * 
 * @author Frederic.Dreyfus
 * 
 */
public class DateChangeEvent extends Component.Event {

    private static final long serialVersionUID = 8745087701675204864L;
    
    private LocalDate localDate;

    public DateChangeEvent(Component source, LocalDate localDate) {
        super(source);
        this.localDate = localDate;
    }

    /**
     * @return the localDate
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

}
