package web.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        final var INVALID_DATA_MESSAGE = "Please set data in correct form";

        try {
            if (request.getParameter("X") == null
                    || request.getParameter("Y") == null
                    || request.getParameter("R") == null) {
                sendError(response, INVALID_DATA_MESSAGE);
                return;
            }

            if (request.getParameter("X").isEmpty()
                    || request.getParameter("Y").isEmpty()
                    || request.getParameter("R").isEmpty()) {
                sendError(response, INVALID_DATA_MESSAGE);
                return;
            }

            if (Double.parseDouble(request.getParameter("X")) < -3
                    || Double.parseDouble(request.getParameter("X")) > 3) {
                sendError(response, INVALID_DATA_MESSAGE);
                return;
            }

            Double.parseDouble(request.getParameter("Y"));
            Double.parseDouble(request.getParameter("R"));

            response.sendRedirect("./areaChecker?" + request.getQueryString());
        } catch (Exception e) {
            sendError(response, e.toString());
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
