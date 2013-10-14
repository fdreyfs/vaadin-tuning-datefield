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


package org.vaadin.addons.tuningdatefield.widgetset.client.ui.events;

import com.google.gwt.event.shared.GwtEvent;

public class DateTextChangeEvent extends GwtEvent<DateTextChangeHandler> {

    private static Type<DateTextChangeHandler> TYPE;

    private String dateText;

    public DateTextChangeEvent(String dateText) {
        this.dateText = dateText;
    }

    @Override
    public Type<DateTextChangeHandler> getAssociatedType() {
        return getType();
    }

    public static Type<DateTextChangeHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<DateTextChangeHandler>();
        }
        return TYPE;
    }

    @Override
    protected void dispatch(DateTextChangeHandler handler) {
        handler.onDateTextChange(this);
    }

    /**
     * @return the dateText
     */
    public String getDateText() {
        return dateText;
    }

}
