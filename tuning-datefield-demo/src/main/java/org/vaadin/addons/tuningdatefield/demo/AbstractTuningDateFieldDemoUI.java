package org.vaadin.addons.tuningdatefield.demo;

import static org.joda.time.DateTimeConstants.JULY;
import static org.joda.time.DateTimeConstants.JUNE;
import static org.joda.time.DateTimeConstants.MAY;
import static org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration.AFTERNOON;
import static org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration.FULLDAY;
import static org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration.MORNING;

import java.util.Locale;
import java.util.Set;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.vaadin.addons.tuningdatefield.CellItemCustomizerAdapter;
import org.vaadin.addons.tuningdatefield.TuningDateField;
import org.vaadin.addons.tuningdatefield.InlineTuningDateField;
import org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration;
import org.vaadin.addons.tuningdatefield.event.CalendarOpenEvent;
import org.vaadin.addons.tuningdatefield.event.CalendarOpenListener;
import org.vaadin.addons.tuningdatefield.event.DateChangeEvent;
import org.vaadin.addons.tuningdatefield.event.DateChangeListener;
import org.vaadin.addons.tuningdatefield.event.MonthChangeEvent;
import org.vaadin.addons.tuningdatefield.event.MonthChangeListener;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.google.gwt.thirdparty.guava.common.collect.Sets;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;

@SuppressWarnings("serial")
public abstract class AbstractTuningDateFieldDemoUI extends UI {

    private FormLayout layout;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        setContent(mainLayout);

        final ComboBox themeComboBox = new ComboBox("Theme");
        themeComboBox.addItem("runo");
        themeComboBox.addItem("reindeer");
        themeComboBox.setNullSelectionAllowed(false);
        String theme = request.getParameter("theme");
        if (!Strings.isNullOrEmpty(theme)) {
            themeComboBox.setValue(theme.substring(5));
        } else {
            themeComboBox.select("reindeer");
        }

        themeComboBox.setImmediate(true);

        themeComboBox.addValueChangeListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                getPage().open("?theme=demo_" + themeComboBox.getValue(), "_self");

            }
        });
        mainLayout.addComponent(themeComboBox);

        layout = new FormLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        mainLayout.addComponent(layout);

        HorizontalLayout tuningDateFieldLayout = new HorizontalLayout();
        tuningDateFieldLayout.setSpacing(true);
        tuningDateFieldLayout.setCaption("Basic US TuningDateField");
        final TuningDateField basicTuningDateField = new TuningDateField();
        basicTuningDateField.setLocale(Locale.US);
        basicTuningDateField.setInvalidAllowed(false);
        tuningDateFieldLayout.addComponent(basicTuningDateField);
        Button toggleEnableButton = new Button("Toggle enabled/disabled");
        toggleEnableButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (basicTuningDateField.isEnabled()) {
                    basicTuningDateField.setEnabled(false);
                } else {
                    basicTuningDateField.setEnabled(true);
                }
            }
        });
        tuningDateFieldLayout.addComponent(toggleEnableButton);
        
        
        Button toggleDateTextReadOnlyButton = new Button("Enable/Disable dateText readOnly");
        toggleDateTextReadOnlyButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (basicTuningDateField.isDateTextReadOnly()) {
                    basicTuningDateField.setDateTextReadOnly(false);
                } else {
                    basicTuningDateField.setDateTextReadOnly(true);
                }
            }
        });
        tuningDateFieldLayout.addComponent(toggleDateTextReadOnlyButton);

        layout.addComponent(tuningDateFieldLayout);
        
        HorizontalLayout tuningInlineDateFieldLayout = new HorizontalLayout();
        tuningInlineDateFieldLayout.setSpacing(true);
        tuningInlineDateFieldLayout.setCaption("US InlineTuningDateField");
        final InlineTuningDateField tuningInlineDateField = new InlineTuningDateField();
        tuningInlineDateField.setLocale(Locale.US);        
        tuningInlineDateFieldLayout.addComponent(tuningInlineDateField);
        VerticalLayout inlineInfosLayout = new VerticalLayout();
        Button toggleControlsEnabledButton = new Button("Enable/Disable controls");
        toggleControlsEnabledButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (tuningInlineDateField.isControlsEnabled()) {
                    tuningInlineDateField.setControlsEnabled(false);
                } else {
                    tuningInlineDateField.setControlsEnabled(true);
                }
            }
        });
        inlineInfosLayout.addComponent(toggleControlsEnabledButton);
        final Label inlineSelectedDateLabel = new Label();
        tuningInlineDateField.addDateChangeListener(new DateChangeListener() {
            
            @Override
            public void dateChange(DateChangeEvent event) {
                inlineSelectedDateLabel.setValue("Date selected : "+event.getLocalDate());
                
            }
        });
        inlineInfosLayout.addComponent(inlineSelectedDateLabel);
        tuningInlineDateFieldLayout.addComponent(inlineInfosLayout);
        layout.addComponent(tuningInlineDateFieldLayout);

        TuningDateField rangeTuningDateField = new TuningDateField("TuningDateField with range");
        rangeTuningDateField.setLocale(Locale.US);
        LocalDate startDate = new LocalDate(2013, MAY, 10);
        LocalDate endDate = new LocalDate(2013, JUNE, 5);
        rangeTuningDateField.setDateRange(startDate, endDate, "The date must be between " + startDate + " and "
                + endDate);
        rangeTuningDateField.setConvertedValue(new LocalDate(2013, MAY, 15));
        layout.addComponent(rangeTuningDateField);

        TuningDateField tuningDateFieldWithPattern = new TuningDateField(
                "French TuningDateField with pattern dd/MM/yyyy");
        tuningDateFieldWithPattern.setLocale(Locale.FRANCE);
        tuningDateFieldWithPattern.setDateTimeFormatter(DateTimeFormat.forPattern("dd/MM/yyyy"));
        tuningDateFieldWithPattern.setConversionError("Date must on the following format dd/MM/yyyy");
        tuningDateFieldWithPattern.setConvertedValue(LocalDate.now());
        layout.addComponent(tuningDateFieldWithPattern);

        TuningDateField tuningDateFieldWithHolidays = new TuningDateField("US TuningDateField with holidays");
        tuningDateFieldWithHolidays.setLocale(Locale.US);
        tuningDateFieldWithHolidays.setCellItemCustomizer(new HolidayCustomizer(Locale.US));
        tuningDateFieldWithHolidays.setConvertedValue(new LocalDate(2013, JULY, 1));
        layout.addComponent(tuningDateFieldWithHolidays);

        final TuningDateField tuningDateFieldWithAbsences = new TuningDateField(
                "US TuningDateField with holidays and absences");
        tuningDateFieldWithAbsences.setLocale(Locale.US);
        tuningDateFieldWithAbsences.setConvertedValue(new LocalDate(2013, MAY, 8));
        // Here we set the cellItemCustomizer just before opening the calendar
        tuningDateFieldWithAbsences.addCalendarOpenListener(new CalendarOpenListener() {
            @Override
            public void calendarOpen(CalendarOpenEvent event) {
                tuningDateFieldWithAbsences.setCellItemCustomizer(new AbsenceCustomizer(Locale.US, event.getYearMonth()));

            }
        });
        // We load absences for the month 
        tuningDateFieldWithAbsences.addMonthChangeListener(new MonthChangeListener() {
            
            @Override
            public void monthChange(MonthChangeEvent event) {
                ((AbsenceCustomizer)tuningDateFieldWithAbsences.getCellItemCustomizer()).loadAbsences(event.getYearMonth());
                
            }
        });
        layout.addComponent(tuningDateFieldWithAbsences);

        TuningDateField tuningDateFieldWithLongServerSideProcess = new TuningDateField(
                "TuningDateField with long server-side process");
        tuningDateFieldWithLongServerSideProcess.setCellItemCustomizer(new CellItemCustomizerAdapter() {

            @Override
            public boolean isEnabled(LocalDate date, TuningDateField calendar) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }

        });
        layout.addComponent(tuningDateFieldWithLongServerSideProcess);

        HorizontalLayout tuningDateFieldWithDataSourceLayout = new HorizontalLayout();
        tuningDateFieldWithDataSourceLayout.setSpacing(true);
        tuningDateFieldWithDataSourceLayout.setCaption("TuningDateField with dataSource");
        final Absence absence = new Absence(LocalDate.now(), AbsenceDuration.FULLDAY);
        BeanItem<Absence> item = new BeanItem<Absence>(absence);

        TuningDateField tuningDateFieldWithDataSource = new TuningDateField(item.getItemProperty("day"));
        FieldGroup binder = new FieldGroup(item);
        binder.setItemDataSource(item);
        final Label label = new Label(absence.getDay().toString());
        tuningDateFieldWithDataSource.addDateChangeListener(new DateChangeListener() {

            @Override
            public void dateChange(DateChangeEvent event) {
                label.setValue("Value in datasource : " + absence.getDay().toString());
            }
        });

        tuningDateFieldWithDataSourceLayout.addComponent(tuningDateFieldWithDataSource);
        tuningDateFieldWithDataSourceLayout.addComponent(label);
        layout.addComponent(tuningDateFieldWithDataSourceLayout);

        HorizontalLayout tuningDateFieldOddEventLayout = new HorizontalLayout();
        tuningDateFieldOddEventLayout.setSpacing(true);
        tuningDateFieldOddEventLayout.setCaption("TuningDateField with odd/even days style");
        TuningDateField tuningDateFieldOddEvent = new TuningDateField();
        tuningDateFieldOddEvent.setCellItemCustomizer(new CellItemCustomizerAdapter() {
            @Override
            public String getStyle(LocalDate date, TuningDateField calendar) {
                return date.getDayOfMonth() % 2 == 0 ? "even" : "odd";
            }
        });

        tuningDateFieldOddEventLayout.addComponent(tuningDateFieldOddEvent);
        layout.addComponent(tuningDateFieldOddEventLayout);

    }

    private static class HolidayCustomizer extends CellItemCustomizerAdapter {

        private HolidayManager holidayManager;
        private Locale locale;

        public HolidayCustomizer(Locale locale) {
            this.locale = locale;
            holidayManager = HolidayManager.getInstance(locale.getCountry().toLowerCase());
        }

        @Override
        public boolean isEnabled(LocalDate date, TuningDateField calendar) {
            return !holidayManager.isHoliday(date);
        }

        @Override
        public String getTooltip(LocalDate date, TuningDateField calendar) {
            if (holidayManager.isHoliday(date)) {
                Holiday holiday = holidayManager.getHolidays(date.toInterval()).iterator().next();
                return holiday.getDescription(locale);
            } else {
                return null;
            }
        }

        @Override
        public String getStyle(LocalDate date, TuningDateField calendar) {
            if (holidayManager.isHoliday(date)) {
                return "holiday";
            } else {
                return null;
            }
        }
    }

    private static class AbsenceCustomizer extends CellItemCustomizerAdapter {

        private Locale locale;
        private HolidayManager holidayManager;
        private Set<Absence> absences;

        public AbsenceCustomizer(Locale locale, YearMonth yearMonth) {
            this.locale = locale;
            this.holidayManager = HolidayManager.getInstance(locale.getCountry().toLowerCase());
            loadAbsences(yearMonth);
        }

        public void loadAbsences(YearMonth yearMonth) {
            absences = Sets.newHashSet();
            // for the month we create 1 morning absence, 1 afternoon and 2 consecutive days
            LocalDate firstTuesday = new LocalDate(yearMonth.getYear(), yearMonth.getMonthOfYear(), 1)
                    .withDayOfWeek(DateTimeConstants.TUESDAY);
            if(firstTuesday.getMonthOfYear() != yearMonth.getMonthOfYear()) {
                firstTuesday = firstTuesday.plusWeeks(1);
            }
            absences.add(new Absence(firstTuesday, MORNING));
            absences.add(new Absence(firstTuesday.plusDays(15), AFTERNOON));
            absences.add(new Absence(firstTuesday.plusDays(23), FULLDAY));
            absences.add(new Absence(firstTuesday.plusDays(24), FULLDAY));

        }

        // We disable for holidays and full day absences
        @Override
        public boolean isEnabled(LocalDate date, TuningDateField calendar) {
            if (holidayManager.isHoliday(date) || absences.contains(new Absence(date, FULLDAY))) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public String getTooltip(LocalDate date, TuningDateField calendar) {
            String tooltip = null;

            if (holidayManager.isHoliday(date)) {
                Holiday holiday = holidayManager.getHolidays(date.toInterval()).iterator().next();
                return holiday.getDescription(locale);
            } else {
                for (Absence absence : absences) {
                    if (absence.getDay().equals(date)) {
                        tooltip = "Absence on the " + date.toString() + " "
                                + absence.getDuration().name().toLowerCase();
                        break;
                    }
                }
            }

            return tooltip;
        }

        @Override
        public String getStyle(LocalDate date, TuningDateField calendar) {
            String style = null;

            if (holidayManager.isHoliday(date)) {
                style = "holiday";
            } else {
                for (Absence absence : absences) {
                    if (absence.getDay().equals(date)) {
                        style = "absence-" + absence.getDuration().name().toLowerCase();
                        break;
                    }
                }
            }

            return style;
        }
    }

}