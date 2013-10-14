vaadin-tuning-datefield
=======================

A DateField Add-On for Vaadin which can be tuned

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


