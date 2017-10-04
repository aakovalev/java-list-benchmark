import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoTest {

    private static final String RESOURCE_URL = "[resource-url]";

    @Test
    public void name() throws Exception {
        Provider provider = Mockito.mock(Provider.class);
        when(provider.getBy(RESOURCE_URL)).thenReturn("A");

        SystemUnderTest sut = new SystemUnderTest(provider);
        String actualResult = sut.useProvider();
        
        verify(provider, times(2)).getBy(anyString());
        assertThat(actualResult, is("AA"));
    }

    class SystemUnderTest {
        private Provider provider;

        public SystemUnderTest(Provider provider) {
            this.provider = provider;
        }

        public String useProvider() {
            return provider.getBy(RESOURCE_URL) + provider.getBy(RESOURCE_URL);
        }
    }

    interface Provider {
        String getBy(String uri);
    }
}
