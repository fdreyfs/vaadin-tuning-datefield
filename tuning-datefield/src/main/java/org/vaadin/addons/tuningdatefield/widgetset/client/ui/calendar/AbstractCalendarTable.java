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

package org.vaadin.addons.tuningdatefield.widgetset.client.ui.calendar;

import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.CalendarItemClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.NextControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.PreviousControlClickEvent;
import org.vaadin.addons.tuningdatefield.widgetset.client.ui.events.ResolutionControlClickEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * A calendar table that holds cell items and controls.<br />
 * This table is ususally composed of :
 * <ol>
 * <li>A row for controls with 3 cells (previous, resolution and next control)</li>
 * <li>X rows for cellItems which can be days, months or years depending on the resolution</li>
 * </ol>
 * 
 * @author Frederic.Dreyfus
 */
public abstract class AbstractCalendarTable extends FlexTable {

    /**
     * The parent {@link TuningDateFieldCalendarWidget}
     */
    protected TuningDateFieldCalendarWidget tuningDateFieldCalendar;

    /**
     * The text in the resolution control cell
     */
    protected String resolutionControlText;

    /**
     * The cell items
     */
    protected CalendarItem[] calendarItems;

    /**
     * True if the controls are enabled
     */
    protected boolean controlsEnabled;

    public AbstractCalendarTable(TuningDateFieldCalendarWidget tuningDateFieldCalendar, String resolutionControlText,
            CalendarItem[] calendarItems, boolean controlsEnabled) {
        super();
        this.tuningDateFieldCalendar = tuningDateFieldCalendar;
        this.resolutionControlText = resolutionControlText;
        this.calendarItems = calendarItems;
        this.controlsEnabled = controlsEnabled;
        setCellSpacing(0);
        setCellPadding(0);

        init();
    }

    /**
     * Returns the number of columns in the table
     * 
     * @return the number of columns in the table
     */
    protected abstract int getNumberOfColumns();

    /**
     * Returns the primary stylename of cells
     * 
     * @return the primary stylename of cells
     */
    protected abstract String getCellItemPrimaryStylename();

    /**
     * Returns the primary stylename of rows
     * 
     * @return the primary stylename of rows
     */
    protected abstract String getRowPrimaryStylename();

    /**
     * The row number of controls (usually 0)
     * 
     * @return row number of controls (usually 0)
     */
    protected int getControlsRow() {
        return 0;
    }

    /**
     * @return The row of the first cellItem : 2 for day resolution as there is a row with day names, 1 for other
     *         calendars
     */
    protected int getFirstCellItemsRow() {
        return 1;
    }

    /**
     * @return the column of the previous control (usually 0)
     */
    protected int getPreviousControlColumn() {
        return 0;
    }

    /**
     * @return the column of the resolution control (usually 1)
     */
    protected int getResolutionControlColumn() {
        return 1;
    }

    /**
     * @return the column of the next control (usually 2)
     */
    protected int getNextControlColumn() {
        return 2;
    }

    private void init() {

        renderHeader();

        renderCalendarItems();

        // There is only one clickHandler for the table which will dispatch the correct events
        addClickHandler(new ClickHandler() {
            Cell selectedCell = null;

            @Override
            public void onClick(ClickEvent event) {
                Cell clickedCell = getCellForEvent(event);
                if (clickedCell.getRowIndex() >= getFirstCellItemsRow()) { // click on cellItem
                    int itemIndex = getItemIndex(clickedCell.getRowIndex(), clickedCell.getCellIndex());
                    CalendarItem calendarItem = calendarItems[itemIndex];
                    if (calendarItem.isEnabled()) {
                        if (selectedCell != null) {
                            getFlexCellFormatter().removeStyleName(selectedCell.getRowIndex(),
                                    selectedCell.getCellIndex(), "selected");
                        }
                        selectedCell = clickedCell;
                        getFlexCellFormatter().addStyleName(selectedCell.getRowIndex(), selectedCell.getCellIndex(),
                                "selected");
                        cellItemClick(itemIndex);
                    }
                } else if (clickedCell.getRowIndex() == getControlsRow()) {
                    if (clickedCell.getCellIndex() == getPreviousControlColumn()) {
                        previousControlClick();
                    } else if (clickedCell.getCellIndex() == getNextControlColumn()) {
                        nextControlClick();
                    } else if (clickedCell.getCellIndex() == getResolutionControlColumn()) {
                        resolutionControlClick();
                    }
                }

            }
        });
    }

    private void renderCalendarItems() {
        for (CalendarItem cellItem : calendarItems) {
            int row = getRow(cellItem.getIndex());
            int column = getColumn(cellItem.getIndex());

            setText(row, column, cellItem.getText());

            StringBuilder styleNamesBuilder = new StringBuilder(getCellItemPrimaryStylename());
            if (cellItem.isEnabled()) {
                styleNamesBuilder.append(" enabled");
            } else {
                styleNamesBuilder.append(" disabled");
            }

            if (cellItem.getStyle() != null && !cellItem.getStyle().isEmpty()) {
                styleNamesBuilder.append(" ").append(cellItem.getStyle());
            }
            if (cellItem.getTooltip() != null && !cellItem.getTooltip().isEmpty()) {
                DOM.setElementAttribute(getFlexCellFormatter().getElement(row, column), "title", cellItem.getTooltip());
            }

            getFlexCellFormatter().addStyleName(row, column, styleNamesBuilder.toString());
        }

        // For each row add stylename
        int numberOfCellItemRows = calendarItems.length / getNumberOfColumns();
        int firstRow = getFirstCellItemsRow();
        int lastRow = getFirstCellItemsRow() + numberOfCellItemRows - 1;
        for (int i = firstRow; i <= lastRow; i++) {
            getRowFormatter().setStyleName(i, getRowPrimaryStylename());
            if (i == firstRow) {
                getRowFormatter().addStyleName(i, "first-" + getRowPrimaryStylename());
            }
            if (i == lastRow) {
                getRowFormatter().addStyleName(i, "last-" + getRowPrimaryStylename());
            }
        }
    }

    protected void renderHeader() {
        renderControls();
    }

    protected void renderControls() {

        renderPreviousControl();

        renderResolutionControl();

        renderNextControl();

        getRowFormatter().setStyleName(getControlsRow(), "controls");

    }

    protected void renderPreviousControl() {
        if (controlsEnabled) {
            setText(getControlsRow(), getPreviousControlColumn(), "<");
            getFlexCellFormatter()
                    .setStylePrimaryName(getControlsRow(), getPreviousControlColumn(), "previous-control");
            getFlexCellFormatter().addStyleName(getControlsRow(), getPreviousControlColumn(), "enabled");
        } else {
            setText(getControlsRow(), getPreviousControlColumn(), "");
            getFlexCellFormatter()
                    .setStylePrimaryName(getControlsRow(), getPreviousControlColumn(), "previous-control");
            getFlexCellFormatter().addStyleName(getControlsRow(), getPreviousControlColumn(), "disabled");
        }

    }

    protected void renderNextControl() {
        if (controlsEnabled) {
            setText(getControlsRow(), getNextControlColumn(), ">");
            getFlexCellFormatter().setStylePrimaryName(getControlsRow(), getNextControlColumn(), "next-control");
            getFlexCellFormatter().addStyleName(getControlsRow(), getNextControlColumn(), "enabled");
        } else {
            setText(getControlsRow(), getNextControlColumn(), "");
            getFlexCellFormatter().setStylePrimaryName(getControlsRow(), getNextControlColumn(), "next-control");
            getFlexCellFormatter().addStyleName(getControlsRow(), getNextControlColumn(), "disabled");
        }

    }

    protected void renderResolutionControl() {
        setText(getControlsRow(), getResolutionControlColumn(), resolutionControlText);
        getFlexCellFormatter()
                .setStylePrimaryName(getControlsRow(), getResolutionControlColumn(), "resolution-control");
        if (controlsEnabled) {
            getFlexCellFormatter().addStyleName(getControlsRow(), getResolutionControlColumn(), "enabled");
        } else {
            getFlexCellFormatter().addStyleName(getControlsRow(), getResolutionControlColumn(), "disabled");
        }

        int colspan = getNumberOfColumns() - 2;
        getFlexCellFormatter().setColSpan(getControlsRow(), getResolutionControlColumn(), colspan);

    }

    /**
     * Returns the row from a cellItem index.
     * 
     * @param itemIndex
     *            the cellItem index
     * @return the row from a cellItem index.
     */
    protected int getRow(int itemIndex) {
        return getFirstCellItemsRow() + itemIndex / getNumberOfColumns();

    }

    /**
     * Returns the column from a cellItem index.
     * 
     * @param itemIndex
     *            the cellItem index
     * @return the column from a cellItem index.
     */
    protected int getColumn(int itemIndex) {
        return itemIndex % getNumberOfColumns();
    }

    /**
     * Returns the cellItem index from its coordinates.
     * 
     * @param row
     *            the row
     * @param column
     *            the column
     * @return the cellItem index from its coordinates.
     */
    protected int getItemIndex(int row, int column) {
        return (row - getFirstCellItemsRow()) * getNumberOfColumns() + column;
    }

    public void cellItemClick(int itemIndex) {
        tuningDateFieldCalendar.fireEvent(new CalendarItemClickEvent(calendarItems[itemIndex].getRelativeDateIndex(),
                itemIndex));
    }

    public void previousControlClick() {
        tuningDateFieldCalendar.fireEvent(new PreviousControlClickEvent());
    }

    public void nextControlClick() {
        tuningDateFieldCalendar.fireEvent(new NextControlClickEvent());
    }

    public void resolutionControlClick() {
        tuningDateFieldCalendar.fireEvent(new ResolutionControlClickEvent());
    }

}
