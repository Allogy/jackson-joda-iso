jackson-joda-iso
================

Provides support for serializing and deserializing Joda objects as ISO 8601 strings using Jackson.

The existing Jackson support for Joda serializes Joda objects as objects specific to the class. This library allows us to serlialize our Joda objects as ISO 8601 strings so that they are highly compatible with other implementations.

    "dateTime" : "2013-07-24T16:30:20.314-06:00",
    "date": "2013-07-22",
    "period": "P17Y4M",
    
Maven Usage
-----------

The project is available in Maven Central.

    <dependency>
        <groupId>com.allogy.json</groupId>
        <artifactId>jackson-joda-iso</artifactId>
         <version>1.0</version>
    </dependency>


Simple Usage Example
--------------------

    @JsonSerialize(using = ISODateTimeSerializer.class)
    @JsonDeserialize(using = ISODateTimeDeserializer.class)
    public DateTime getDateTime()
    {
        return dateTime;
    }

    @JsonSerialize(using = ISODateTimeSerializer.class)
    @JsonDeserialize(using = ISODateTimeDeserializer.class)
    public void setDateTime(DateTime dateTime)
    {
        this.dateTime = dateTime;
    }
