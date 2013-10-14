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

public class PreviousControlClickEvent extends GwtEvent<PreviousControlClickHandler> {

    private static Type<PreviousControlClickHandler> TYPE;


    public PreviousControlClickEvent() {
     
    }

    @Override
    public Type<PreviousControlClickHandler> getAssociatedType() {
        return getType();
    }

    public static Type<PreviousControlClickHandler> getType() {
        if (TYPE == null) {
            TYPE = new Type<PreviousControlClickHandler>();
        }
        return TYPE;
    }

    @Override
    protected void dispatch(PreviousControlClickHandler handler) {
        handler.onPreviousControlClick(this);
    }

  
}

