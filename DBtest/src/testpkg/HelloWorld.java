package testpkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		final String URL
	    = "jdbc:mysql://localhost:3306/lesson?serverTimezone=JST";
	    final String USER = "root";
	    final String PASS = "";
	    final String SQL = "select * from lesson;";

	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

	    try(Connection conn =
	            DriverManager.getConnection(URL, USER, PASS);
	        PreparedStatement ps = conn.prepareStatement(SQL)){



	        try(ResultSet rs = ps.executeQuery()){
	        	response.setContentType("text/html");
	        	PrintWriter out = response.getWriter();
	        	out.println("<html>");
	        	out.println("<head>");

	        	out.println("</head>");
	        	out.println("<title>Hello World!</title>");
	        	out.println("<body>");
	        	while(rs.next()){
	        		out.println("<h1>"+ rs.getString("name") +"</h1>");
	        	}
	        	out.println("</body>");
	        	out.println("</html>");
	        };
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        System.out.println("処理が完了しました");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
