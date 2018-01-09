/*
 * Copyright 2017 Uwe Plonus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sw4j.gson.adapter;

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

    /** The default constructor. */
    public OffsetDateTimeAdapter() { }

    /**
     * <p>
     * Writes out the given OffsetDateTime as ISO formatted string.
     * </p>
     *
     * @param out the JsonWriter where the data should be written to.
     * @param value the OffsetDateTime to write.
     * @throws IOException is the writing fails.
     */
    @Override
    public void write(final JsonWriter out, final OffsetDateTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.jsonValue(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value));
        }
    }

    /**
     * <p>
     * Reads in a value formatted in ISO format as OffsetDateTime.
     * </p>
     *
     * @param in the JsonReader to read from.
     * @return the parsed OffsetDateTime.
     * @throws IOException if the reading fails.
     */
    @Override
    public OffsetDateTime read(final JsonReader in) throws IOException {
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
