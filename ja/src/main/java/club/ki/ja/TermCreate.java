package club.ki.ja;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonSyntaxException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@WebServlet(name="TermCreate", value="/terms")
public class TermCreate extends HttpServlet {
    private static final long serialVersionUID = 2717269032138762174L;

	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, JsonSyntaxException {

        Gson g = new Gson();
        Term term = g.fromJson(
          new InputStreamReader(request.getInputStream()),
          Term.class);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql:///%s", "ja-deck"));
        config.setUsername("postgres");
        config.setPassword("123H5J5e0JcxDGdo");

        DataSource pool = new HikariDataSource(config);

        response.setContentType("application/json");
        response.getWriter().println("Term Create");
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}

// 123H5J5e0JcxDGdo
// cloud_sql_proxy -dir=/home/gitpod/cloudsql