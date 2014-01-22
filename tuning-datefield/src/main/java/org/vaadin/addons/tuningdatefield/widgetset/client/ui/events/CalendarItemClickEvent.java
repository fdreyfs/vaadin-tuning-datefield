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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.GwtEvent;

public class CalendarItemClickEvent extends GwtEvent<CalendarItemClickHandler> {

    private static Type<CalendarItemClickHandler> TYPE;

    private final Integer relativeDateIndex;
    private final Integer itemIndex;
    private final ClickEvent clickEvent;

    public CalendarItemClickEvent(Integer relativeDateIndex, Integer itemIndex, ClickEvent clickEvent) {
        this.relativeDateIndex = relativeDateIndex;
        this.itemIndex = itemIndex;
        this.clickEvent = clickEvent;
    }

    @Override
    public Type<CalendarItemClickHandler> getAssociatedType() {
        return getType();
    }

    public static Type<CalendarItemClickHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<CalendarItemClickHandler>();
        }
        return TYPE;
    }

    @Override
    protected void dispatch(CalendarItemClickHandler handler) {
        handler.onCalendarItemClick(this);
    }

    /**
     * @return the relativeDateIndex
     */
    public Integer getRelativeDateIndex() {
        return relativeDateIndex;
    }

  
    /**
     * @return the itemIndex
     */
    public Integer getItemIndex() {
        return itemIndex;
    }

    public ClickEvent getClickEvent() {
        return clickEvent;
    }


}
