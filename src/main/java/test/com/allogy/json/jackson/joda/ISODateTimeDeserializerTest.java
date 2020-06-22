package test.com.allogy.json.jackson.joda;

import com.allogy.json.jackson.joda.ISODateTimeDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Payal Pandey
 * @since Jun 18, 2020
 */
@RunWith(Parameterized.class)
public class ISODateTimeDeserializerTest
{

    private JsonParser jsonParser;
    private DeserializationContext deserializationContext;

    @Parameterized.Parameter()
    public String datePattern;

    @Parameterized.Parameter(1)
    public String inputDate;

    @Parameterized.Parameter(2)
    public String expected;

    @Before
    public void setUp()
    {
        jsonParser = mock(JsonParser.class);
        deserializationContext = mock(DeserializationContext.class);
    }

    @Parameterized.Parameters(name = "{index}: Test with datePattern ={0}, inputDate ={1}, expected ={2}")
    public static Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                {"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "2001-07-04T12:08:56.235-07:00", "2001-07-04T12:08:56.235-07:00"},
                {"yyyy-MM-dd'T'HH:mm:ssZ", "2001-07-04T12:08:56-07:00", "2001-07-04T12:08:56.000-07:00"}};
        return Arrays.asList(data);
    }

    public ISODateTimeDeserializer createObjectUnderTest()
    {
        return new ISODateTimeDeserializer();
    }

    @Test
    public void test_deserialize() throws IOException
    {

        when(jsonParser.getText()).thenReturn(inputDate);

        DateTime result = createObjectUnderTest().deserialize(jsonParser, deserializationContext);
        assertThat(result.toString(), equalTo(expected));
    }
}