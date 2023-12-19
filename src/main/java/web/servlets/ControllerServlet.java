package web.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.managers.Parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        final var INVALID_DATA_MESSAGE = "Please set data in correct form";

        try {
            if (request.getParameter("X") == null
                    || request.getParameter("Y") == null
                    || request.getParameter("R") == null) {
                throw new Exception(INVALID_DATA_MESSAGE);
            }

            if (request.getParameter("X").isEmpty()
                    || request.getParameter("Y").isEmpty()
                    || request.getParameter("R").isEmpty()) {
                throw new Exception(INVALID_DATA_MESSAGE);
            }

            if (!Parser.isCorrect(request.getParameter("X"),
                    request.getParameter("Y"),
                    request.getParameter("R"))) {
                throw new Exception(INVALID_DATA_MESSAGE);
            }

            response.sendRedirect("./areaChecker?" + request.getQueryString());
        } catch (Exception e) {
            //sendError(response, e.toString());
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        }
    }

    private void sendError(HttpServletResponse response, String errorMessage)
            throws IOException {
        var gson = new Gson();
        Map<String, Object> json = new HashMap<>() {{
            put("error", errorMessage);
            put("status", "UNPROCESSABLE_ENTITY");
        }};

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(json));
        response.setStatus(422);
    }
}
