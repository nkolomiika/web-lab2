package web.servlets;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.managers.PointManager;
import web.models.Point;
import web.models.PointData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/areaChecker")
public class AreaCheckerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            var x = Double.parseDouble(request.getParameter("X"));
            var y = Double.parseDouble(request.getParameter("Y"));
            var r = Double.parseDouble(request.getParameter("R"));
            var pointData = new PointData(new Point(x, y), r);

            var context = getServletContext();

            if (context.getAttribute("points") == null)
                context.setAttribute("points", new PointManager());

            var points = (PointManager) context.getAttribute("points");
            points.addPoint(pointData);
            context.setAttribute("points", points);

            var action = request.getParameter("action");
            if ("submitForm".equals(action)) {
                request.setAttribute("X", x);
                request.setAttribute("Y", y);
                request.setAttribute("R", r);
                request.setAttribute("result", pointData.getStatus());

                var dispatcher = request.getRequestDispatcher("./result.jsp");
                dispatcher.forward(request, response);

            } else if ("checkPoint".equals(action)) {
                var gson = new Gson();
                Map<String, Object> json = new HashMap<>();
                json.put("x", x);
                json.put("y", y);
                json.put("r", r);
                json.put("result", pointData.getStatus());
                var message = gson.toJson(json);

                response.setContentType("application/json");
                response.getWriter().write(message);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("./index.jsp").forward(request, response);
        }
    }
}
