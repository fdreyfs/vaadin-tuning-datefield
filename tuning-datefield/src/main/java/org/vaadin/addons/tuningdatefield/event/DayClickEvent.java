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

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.Component;

/**
 * Event dispatched when user clicks on a day in the calendar (before date change is made).
 * 
 * @author Frederic.Dreyfus
 * 
 */
public class DayClickEvent extends Component.Event {

    private static final long serialVersionUID = 8745087701675204864L;

    private final LocalDate localDate;
    private final MouseEventDetails mouseDetails;

    public DayClickEvent(Component source, MouseEventDetails mouseDetails, LocalDate localDate) {
        super(source);
        this.mouseDetails = mouseDetails;
        this.localDate = localDate;
    }

    public MouseEventDetails getMouseDetails() {
        return mouseDetails;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

}
