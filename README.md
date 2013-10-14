vaadin-tuning-datefield
=======================

A DateField Add-On for Vaadin which can be tuned.

This basically will display a TextBox and a calenar toggle button using the Vaadin built-in stylenames.

The model used for the value is a <a href="www.joda.org/joda-time">Joda</a> <a href="http://joda-time.sourceforge.net/apidocs/org/joda/time/LocalDate.html">LocalDate</a>.
Thus there is a transitive dependency to Joda Time 2.1

## Installation

### Add the project dependency :
Maven :
```xml
<dependency>
    <groupId>org.vaadin.addons</groupId>
    <artifactId>tuning-datefield</artifactId>
    <version>${tuning-datefield.version}</version>
</dependency>
```

Ivy :
```xml
	<dependency org="org.vaadin.addons" name="tuning-datefield"
			rev="&tuning-datefield.version;" />
```

### Inherit the module in your gwt.xml widgetset file
```xml
<module>

	....
	
	<inherits
		name="org.vaadin.addons.tuningdatefield.widgetset.TuningDateFieldWidgetset" />

</module>
```


