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


import org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar.TuningDateFieldCalendarWidget;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarItemClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarItemClickHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.NextControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.NextControlClickHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.PreviousControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.PreviousControlClickHandler;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.ResolutionControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.ResolutionControlClickHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.vaadin.addons.tuningdatefield.InlineTuningDateField.class)
public class InlineTuningDateFieldConnector extends AbstractFieldConnector {
    private static final long serialVersionUID = 5137839525617663928L;

    @Override
    protected void init() {
        super.init();

        final InlineTuningDateFieldRpc rpc = getRpcProxy(InlineTuningDateFieldRpc.class);

        getWidget().addCalendarItemClickHandler(new CalendarItemClickHandler() {

            @Override
            public void onCalendarItemClick(CalendarItemClickEvent event) {
                rpc.calendarItemClicked(event.getRelativeDateIndex());
            }
        });

        getWidget().addPreviousControlClickHandler(new PreviousControlClickHandler() {

            @Override
            public void onPreviousControlClick(PreviousControlClickEvent event) {
                rpc.previousControlClicked();
            }
        });

        getWidget().addNextControlClickHandler(new NextControlClickHandler() {

            @Override
            public void onNextControlClick(NextControlClickEvent event) {
                rpc.nextControlClicked();
            }
        });

        getWidget().addResolutionControlClickHandler(new ResolutionControlClickHandler() {

            @Override
            public void onResolutionControlClick(ResolutionControlClickEvent event) {
                rpc.resolutionControlClicked();
            }
        });

    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {

        getWidget().setCalendarResolutionText(getState().getCalendarResolutionText());
        getWidget().setWeekHeaderNames(getState().getWeekHeaderNames());

        getWidget().setControlsEnabled(getState().isControlsEnabled());

        getWidget().setCalendarResolution(getState().getCalendarResolution());

        getWidget().setCalendarItems(getState().getCalendarItems());

        getWidget().redraw(true);

        super.onStateChanged(stateChangeEvent);

    }

    @Override
    protected Widget createWidget() {
        return GWT.create(TuningDateFieldCalendarWidget.class);
    }

    @Override
    public TuningDateFieldState getState() {
        return (TuningDateFieldState) super.getState();
    }

    @Override
    public TuningDateFieldCalendarWidget getWidget() {
        return (TuningDateFieldCalendarWidget) super.getWidget();
    }

}
