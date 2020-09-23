# jaxb-global-binding-plugin
Jaxb plugin for defining global bindings options as cli options without specifing external bindings file.

### Using

-simple
  add ```<xjc:simple/>```

-serializable
  add ```<xjc:serializable/>```

-no-element-property
  add ```<xjc:generateElementProperty>false</xjc:generateElementProperty>```

-date-type dateType
  for ```xjc:javaType xmlType="xs:date"``` set ```name=dateType```
-date-adapter dateTypeAdapter
  for ```xjc:javaType xmlType="xs:date"``` set ```adapter=dateTypeAdapter```

-dateTime-type dateTimeType
  for ```xjc:javaType xmlType="xs:dateTime"``` set ```name=dateTimeType```
-dateTime-adapter dateTimeAdapter
  for ```xjc:javaType xmlType="xs:dateTime"``` set ```adapter=dateTimeAdapter```

-time-type timeType
  for ```xjc:javaType xmlType="xs:time"``` set ```name=timeType```
-time-adapter timeAdapter
  for ```xjc:javaType xmlType="xs:time"``` set ```adapter=timeAdapter```

E.g.:	xjc -Xgb\\\
	-simple\\\
	-serializable\\\
	-date-type java.time.LocalDate -date-adapter package.XmlDateAdapter\\\
	-time-type java.time.LocalTime -time-adapter package.XmlTimeAdapter\\\
	-dateTime-type java.time.LocalDateTime -dateTime-adapter package.XmlDateTimeAdapter
	
will produce result as using the following bindings.xjb file:
```
<jxb:bindings
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:jxb="http://java.sun.com/xml/ns/jaxb"
    xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
    jxb:extensionBindingPrefixes="xjc"
    version="2.1">
    
    <jxb:globalBindings generateElementProperty="false">
        <xjc:simple/>
        <xjc:generateElementProperty>false</xjc:generateElementProperty>
        <xjc:serializable/>

        <xjc:javaType name="java.time.LocalDate" xmlType="xs:date" adapter="package.XmlDateAdapter"/>
        <xjc:javaType name="ava.time.LocalDateTime" xmlType="xs:dateTime" adapter="package.XmlDateTimeAdapter"/>
        <xjc:javaType name="java.time.LocalTime" xmlType="xs:time" adapter="package.XmlTimeAdapter"/>
    </jxb:globalBindings>
</jxb:bindings>
```
