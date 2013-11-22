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

import com.vaadin.ui.Component;

/**
 * Event dispatched when the resolution changed in the calendar.
 * 
 * @author Frederic.Dreyfus
 * 
 */
public class ResolutionChangeEvent extends Component.Event {

    private static final long serialVersionUID = 5554093657394470765L;

    private final Resolution resolution;

    public ResolutionChangeEvent(Component source, Resolution resolution) {
        super(source);
        this.resolution = resolution;
    }

    public static enum Resolution {
        DAY, MONTH, YEAR;
    }

    /**
     * @return the resolution
     */
    public Resolution getResolution() {
        return resolution;
    }
}
