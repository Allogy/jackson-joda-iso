/*
 * Copyright (c) 2013 Allogy Interactive.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.allogy.jackson.joda;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Deserializes {@link LocalDate} objects as ISO strings
 *
 * @author David Venable
 */
public class ISOLocalDateDeserializer extends JsonDeserializer<LocalDate>
{
    private final DateTimeFormatter localDateFormatter;

    public ISOLocalDateDeserializer()
    {
        localDateFormatter = ISODateTimeFormat.localDateParser();
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        String text = jsonParser.getText();
        try
        {
            return localDateFormatter.parseLocalDate(text);
        }
        catch (Throwable throwable)
        {
            throw new InvalidFormatException(throwable.getMessage(), text, String.class);
        }
    }
}
