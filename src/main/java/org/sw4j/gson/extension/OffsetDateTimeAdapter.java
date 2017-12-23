package org.sw4j.gson.extension;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This is a type adapter for gson to read and write OffsetDateTime objects as ISO timestamp.
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class OffsetDateTimeAdapter extends TypeAdapter<OffsetDateTime> {

    public OffsetDateTimeAdapter() { }

    @Override
    public void write(JsonWriter out, OffsetDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.jsonValue(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value));
        }
    }

    @Override
    public OffsetDateTime read(JsonReader in) throws IOException {
        OffsetDateTime result = null;
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
        } else {
            String rawDate = in.nextString();
            result = OffsetDateTime.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(rawDate));
        }
        return result;
    }

}
