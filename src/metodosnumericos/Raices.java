package metodosnumericos;

import javax.swing.JOptionPane;

public class Raices {

    public static void main(String[] args) {
        Raices metodos = new Raices();

        int opciones;
        double resultados[] = new double[2];
        do {
            opciones = Integer.parseInt(JOptionPane.showInputDialog(
                    "-------Meun-------\n"
                    + "1-Metodo Biseccion\n"
                    + "2-Metodo Falsa Posicion\n"
                    + "3-Metodo Secante\n"
                    + "4-Metodo Newton-Raphson\n"
                    + "5-Salir"));
            if (opciones != 5 && opciones >= 1 && opciones <= 5) {
                resultados = metodos.opciones(opciones);

                System.out.println("La raiz es: " + resultados[0]);
                System.out.println("El error es: " + resultados[1]);
            }
        } while (opciones != 5 && opciones >= 1 && opciones <= 5);
    }

    private double funcion(double x) {
        return Math.pow(x, 2) - 16;
    }

    private double derivada(double x) {
        return (funcion(x + 0.000001) - funcion(x)) / 0.000001;
    }

    private double[] newtonRaphson(double x1, double error) {
        double x2 = 0, errorAbs = 100;
        while (errorAbs > error) {
            x2 = x1 - funcion(x1) / derivada(x1);
            errorAbs = Math.abs((x2 - x1) / x2) * 100;
            x1 = x2;
        }
        return new double[]{x1, errorAbs};
    }

    private double[] falsaPosicion(double a, double b, double error) {
        double m, fm, errorAbs = 1000, mAnt, fa, fb;

        fa = funcion(a);
        fb = funcion(b);
        m = ((b * fa) - (a * fb)) / (fa - fb);
        fm = funcion(m);

        System.out.println(" a   |   fa   |   b   |   fb   |   m   |   fm   ");

        while (errorAbs > error) {
            impresion(a, fa, b, fb, m, fm);
            if ((fa * fm) >= 0) {
                a = m;
                fa = fm;
            } else {
                b = m;
                fb = fm;
            }
            mAnt = m;
            m = ((b * fa) - (a * fb)) / (fa - fb);
            fm = funcion(m);
            errorAbs = Math.abs((m - mAnt) / m) * 100;
        }
        return new double[]{m, errorAbs};
    }

    private double[] biseccion(double a, double b, double error) {
        double m, fm, errorAbs = 100, fa, fb, mAnt;
        double algo[] = new double[]{1, 2};
        m = (a + b) / 2;
        fa = funcion(a);
        fb = funcion(b);
        fm = funcion(m);

        System.out.println(" a   |   fa   |   b   |   fb   |   m   |   fm   ");

        while (error < errorAbs) {
            mAnt = m;
            impresion(a, fa, b, fb, m, fm);
            if (fa * fm > 0) {
                a = m;
                fa = funcion(a);
            } else {
                b = m;
                fb = funcion(b);
            }
            m = (a + b) / 2;
            fm = funcion(m);

            errorAbs = Math.abs(((m - mAnt) / m) * 100);
        }

        return new double[]{m, errorAbs};
    }

    private double[] secante(double a, double b, double error) {
        double fa, fm, errorAbs, m, fb, mAnt;

        fa = funcion(a);
        fb = funcion(b);
        m = b - (((b - a) * fb ) / (fb - fa)); 
        fm = funcion(m);

        System.out.println(" a   |   fa   |   b   |   fb   |   m   |   fm   ");

        do {
            mAnt = m;
            impresion(a, fa, b, fb, m, fm);
            a = b;
            b = m;

            fa = fb;
            fb = fm;

            m = b - (((b - a) * fb ) / (fb - fa)); 
            fm = funcion(m);

            errorAbs = Math.abs((m - mAnt) / m) * 100;
        }while (errorAbs > error);
        return new double[]{m, errorAbs};
    }

    private double[] opciones(int opciones) {
        double a, b, error;
        switch (opciones) {
            case 1:
                a = Double.parseDouble(
                        JOptionPane.showInputDialog("ingrese A"));
                b = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese B"));
                error = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese el error en %"));
                return biseccion(a, b, error);
            case 2:
                a = Double.parseDouble(
                        JOptionPane.showInputDialog("ingrese A"));
                b = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese B"));
                error = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese el error en %"));
                return falsaPosicion(a, b, error);
            case 3:
                a = Double.parseDouble(
                        JOptionPane.showInputDialog("ingrese A"));
                b = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese B"));
                error = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese el error en %"));
                return secante(a, b, error);
            case 4:
                a = Double.parseDouble(
                        JOptionPane.showInputDialog("ingrese A"));
                error = Double.parseDouble(
                        JOptionPane.showInputDialog("Ingrese el error en %"));
                return newtonRaphson(a, error);
        }
        return new double[]{0, 0};
    }

    private void impresion(double a, double fa,
            double b, double fb, double m, double fm) {
        System.out.printf("%.5f|%.5f|%.5f|%.5f|%.5f|%.5f\n",a,fa,b,fb,m,fm);
    }

}
