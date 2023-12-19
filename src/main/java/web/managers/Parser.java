package web.managers;

public class Parser {

    public static boolean isCorrect(String x, String y, String r) {
        try {
            return checkX(Double.parseDouble(x))
                    && checkY(Double.parseDouble(y))
                    && checkR(Double.parseDouble(r));
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkY(double y) {
        return y >= -3 || y <= 3;
    }

    private static boolean checkX(double x) {
        double[] values = {
                -1, -1.5, -2, 0.5, 0, -0.5, 1, 1.5, 2
        };

        boolean flag = false;
        for (double element : values) {
            if (element == x) {
                flag = true;
                break;
            }
        }
        return flag;

    }

    private static boolean checkR(double r) {
        double[] values = {
                1, 1.5, 2, 2.5, 3
        };

        boolean flag = false;
        for (double element : values) {
            if (element == r) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
