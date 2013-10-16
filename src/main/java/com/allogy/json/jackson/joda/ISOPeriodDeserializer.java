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

package com.allogy.json.jackson.joda;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.joda.time.Period;

import java.io.IOException;

/**
 * Deserializes {@link Period} objects as ISO strings
 *
 * @author David Venable
 */
public class ISOPeriodDeserializer extends StdDeserializer<Period>
{
    public ISOPeriodDeserializer()
    {
        super(Period.class);
    }

    @Override
    public Period deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        String textValue = jsonParser.getText();
        try
        {
            return new Period(textValue);
        }
        catch (Throwable throwable)
        {
            throw new InvalidFormatException(throwable.getMessage(), textValue, String.class);
        }
    }
}
