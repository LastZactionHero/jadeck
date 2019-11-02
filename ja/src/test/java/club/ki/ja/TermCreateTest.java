package club.ki.ja;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(JUnit4.class)
public class TermCreateTest {
  // Set up a helper so that the ApiProxy returns a valid environment for local testing.
  private final LocalServiceTestHelper helper = new LocalServiceTestHelper();

  @Mock private HttpServletResponse mockResponse;
  private StringWriter responseWriter;
  private TermCreate servletUnderTest;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    helper.setUp();

    // Set up a fake HTTP response.
    responseWriter = new StringWriter();
    when(mockResponse.getWriter()).thenReturn(new PrintWriter(responseWriter));

    servletUnderTest = new TermCreate();
  }

  @After public void tearDown() {
    helper.tearDown();
  }

  @Test
  public void doPostCreatesTerm() throws Exception {
    InputStream is = getClass().getClassLoader().getResourceAsStream("term.json");

    Gson g = new Gson();
    Term term = g.fromJson(new InputStreamReader(is), Term.class);
    String json = g.toJson(term);
    MockHttpServletRequest mockRequest = new MockHttpServletRequest();
    mockRequest.setContent(json.getBytes());
    mockRequest.setContentType("application/json");

    servletUnderTest.doPost(mockRequest, mockResponse);
    // assertEquals(201, mockResponse.getStatus());
  }
}