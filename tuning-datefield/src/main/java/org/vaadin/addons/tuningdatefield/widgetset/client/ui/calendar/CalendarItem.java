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

import java.io.Serializable;

/**
 * A calendar item which is sent from the server to the calendar.<br />
 * It will hold all data for each cell's rendering.
 * 
 * @author Frederic.Dreyfus
 * 
 */
public class CalendarItem implements Serializable {

    private static final long serialVersionUID = -6465618471446068512L;

    /**
     * The index in the table
     */
    private Integer index;

    /**
     * can be dayOfMonth, monthOfYear, or year.<br />
     * In the case of dayOfMonth, it is negative if not current month displayed
     */
    private Integer relativeDateIndex;

    /**
     * The style of the cell
     */
    private String style;

    /**
     * <code>true</code> if the cell is enabled
     */
    private boolean enabled;

    /**
     * The tooltip
     */
    private String tooltip;

    /**
     * <code>true</code> if the item represents the current day, month or year
     */
    private boolean current;

    /**
     * The text as it will appear in the table cell
     */
    private String text;

    public CalendarItem() {

    }

    @Override
    public String toString() {
        return "CalendarItem [index=" + index + ", relativeDateIndex=" + relativeDateIndex + ", style=" + style
                + ", enabled=" + enabled + ", tooltip=" + tooltip + ", current=" + current + ", text=" + text + "]";
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index
     *            the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the relativeDateIndex
     */
    public Integer getRelativeDateIndex() {
        return relativeDateIndex;
    }

    /**
     * @param relativeDateIndex
     *            the relativeDateIndex to set
     */
    public void setRelativeDateIndex(Integer relativeDateIndex) {
        this.relativeDateIndex = relativeDateIndex;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style
     *            the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * @param tooltip
     *            the tooltip to set
     */
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @return the current
     */
    public boolean isCurrent() {
        return current;
    }

    /**
     * @param current
     *            the current to set
     */
    public void setCurrent(boolean current) {
        this.current = current;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
