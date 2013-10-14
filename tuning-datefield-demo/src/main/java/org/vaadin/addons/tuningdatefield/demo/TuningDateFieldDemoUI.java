package org.vaadin.addons.tuningdatefield.demo;

import static org.joda.time.DateTimeConstants.JULY;
import static org.joda.time.DateTimeConstants.JUNE;
import static org.joda.time.DateTimeConstants.MAY;
import static org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration.AFTERNOON;
import static org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration.FULLDAY;
import static org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration.MORNING;

import java.util.Locale;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.vaadin.addons.tuningdatefield.CellItemCustomizerAdapter;
import org.vaadin.addons.tuningdatefield.TuningDateField;
import org.vaadin.addons.tuningdatefield.demo.Absence.AbsenceDuration;
import org.vaadin.addons.tuningdatefield.event.DateChangeEvent;
import org.vaadin.addons.tuningdatefield.event.DateChangeListener;

import com.google.gwt.thirdparty.guava.common.collect.Sets;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import de.jollyday.Holiday;
import de.jollyday.HolidayManager;

@SuppressWarnings("serial")
@Theme("tuning_datefield_demo")
public class TuningDateFieldDemoUI extends UI {

    private FormLayout layout;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = true, ui = TuningDateFieldDemoUI.class, widgetset = "org.vaadin.addons.tuningdatefield.demo.widgetset.TuningDateFieldDemoWidgetset")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        layout = new FormLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        HorizontalLayout tuningDateFieldLayout = new HorizontalLayout();
        tuningDateFieldLayout.setSpacing(true);
        tuningDateFieldLayout.setCaption("Basic US TuningDateField");
        final TuningDateField basicTuningDateField = new TuningDateField();
        basicTuningDateField.setLocale(Locale.US);
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

        layout.addComponent(tuningDateFieldLayout);

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

        TuningDateField tuningDateFieldWithAbsences = new TuningDateField(
                "US TuningDateField with holidays and absences");
        tuningDateFieldWithAbsences.setLocale(Locale.US);
        tuningDateFieldWithAbsences.setConvertedValue(new LocalDate(2013, MAY, 8));
        tuningDateFieldWithAbsences.setCellItemCustomizer(new AbsenceCustomizer(Locale.US));
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

        private HolidayCustomizer holidayDayCustomizer;

        public AbsenceCustomizer(Locale locale) {
            holidayDayCustomizer = new HolidayCustomizer(locale);
        }

        Set<Absence> absences = Sets.newHashSet(new Absence(2013, MAY, 6, MORNING), new Absence(2013, MAY, 17,
                AFTERNOON), new Absence(2013, MAY, 29, FULLDAY), new Absence(2013, MAY, 30, FULLDAY));

        // We disable for holidays and full day absences
        @Override
        public boolean isEnabled(LocalDate date, TuningDateField calendar) {
            if (!holidayDayCustomizer.isEnabled(date, calendar) || absences.contains(new Absence(date, FULLDAY))) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public String getTooltip(LocalDate date, TuningDateField calendar) {
            String holidayTooltip = holidayDayCustomizer.getTooltip(date, calendar);
            if (holidayTooltip != null) {
                return holidayTooltip;
            }
            for (Absence absence : absences) {
                if (absence.getDay().equals(date)) {
                    return "Absence on the " + date.toString() + " " + absence.getDuration().name().toLowerCase();
                }
            }
            return null;
        }

        @Override
        public String getStyle(LocalDate date, TuningDateField calendar) {
            StringBuilder styleBuilder = new StringBuilder();
            String holidayStyle = holidayDayCustomizer.getStyle(date, calendar);
            if (holidayStyle != null) {
                styleBuilder.append(holidayStyle);
            }

            for (Absence absence : absences) {
                if (absence.getDay().equals(date)) {
                    styleBuilder.append("absence-" + absence.getDuration().name().toLowerCase());
                    break;
                }
            }
            return styleBuilder.toString();
        }
    }

}