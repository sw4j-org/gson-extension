package org.sw4j.gson.adapter;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OffsetDateTimeAdapterTest {

    private String dateTimeString;

    private OffsetDateTime dateTime;

    @BeforeMethod
    public void setUp() {
        dateTime = OffsetDateTime.now();
        dateTimeString = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(dateTime);
    }

    @Test
    public void testCreation() {
        OffsetDateTimeAdapter adapter = new OffsetDateTimeAdapter();
    }

    @Test
    public void testParseNull() throws IOException {
        OffsetDateTimeAdapter adapter = new OffsetDateTimeAdapter();
        Reader reader = new StringReader("null");
        JsonReader jsonReader = new JsonReader(reader);

        OffsetDateTime dateTime = adapter.read(jsonReader);
        Assert.assertNull(dateTime, "Expected dateTime to be null.");
    }

    @Test
    public void testParseDate() throws IOException {
        OffsetDateTimeAdapter adapter = new OffsetDateTimeAdapter();
        Reader reader = new StringReader(new StringBuilder("\"").append(dateTimeString).append("\"").toString());
        JsonReader jsonReader = new JsonReader(reader);

        OffsetDateTime dt = adapter.read(jsonReader);
        Assert.assertNotNull(dt, "Expected dateTime not to be null.");
        Assert.assertEquals(dt, dateTime,
                "Expected the date to be the same as during construction.");
    }

    @Test
    public void testWriteNull() throws IOException {
        OffsetDateTimeAdapter adapter = new OffsetDateTimeAdapter();
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(writer);

        adapter.write(jsonWriter, null);
        Assert.assertEquals(writer.toString(), "null", "Expected dateTime to be null.");
    }

    @Test
    public void testWriteDate() throws IOException {
        OffsetDateTimeAdapter adapter = new OffsetDateTimeAdapter();
        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(writer);

        adapter.write(jsonWriter, dateTime);
        Assert.assertEquals(writer.toString(), dateTimeString, "Expected dateTime to be written.");
    }

}
