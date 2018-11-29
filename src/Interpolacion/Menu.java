package Interpolacion;

import javax.swing.JOptionPane;

public class Menu {

    private int n = 0;
    private double x[];
    private double fx[];
    private double newton[];

    public static void main(String[] args) {
        Menu m = new Menu();
        int op;

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("seleccione\n"
                    + "1-ingresar datos\n"
                    + "2-interpolacion de newton\n"
                    + "3-interpolacion de lagrange\n"
                    + "4-evaluar polinomio newton\n"
                    + "5-salir"));
            m.opciones(op);
        } while (op != 5);
    }

    public void opciones(int op) {

        switch (op) {
            case 1://Join data
                n = Integer.parseInt(JOptionPane.showInputDialog(
                        "Ingrese el grado del polinomio"));
                x = ingresar(n, 1);
                fx = ingresar(n, 2);
                break;
            case 2:// execute interpolation of Newton
                newton = interpolarNewton(n, x, fx);
                break;
            case 3://execute interpolation of Lagrange
                interpolarLagrange(n, x, fx);
                break;
            case 4://evaluate newton polynomial
                evaluarNewton(n, newton, x);
                break;

            default:
        }
    }

    public double[] ingresar(int n, int s) {
        double[] in = new double[n + 1];
        for (int i = 0; i < n + 1; i++) {
            in[i] = Double.parseDouble(JOptionPane.showInputDialog(
                    "ingrese " + (s == 1 ? "x " : "fx ") + "[" + i + "]"));
        }

        return in;
    }

    public double[] interpolarNewton(int n, double[] x, double[] fx) {
        double res[] = new double[n + 1];

        for (int i = 0; i <= n; i++) {
            res[i] = MetodosInterpolacion.factorInterpolacionNewton(i, x, fx);
            System.out.print((i != 0 ? (res[i] >= 0 ? " + " : " ") : "")
                    + res[i] + (i != 0 ? "x^" + i : ""));
        }
        System.out.println("");

        return res;
    }

    public void interpolarLagrange(int n, double[] x, double[] fx) {
        double[] l = new double[n + 1];
        double eval = Double.parseDouble(JOptionPane.showInputDialog(
                "ingrese el valor a evaluar"));
        double res = 0;

        for (int i = 0; i <= n; i++) {
            l[i] = MetodosInterpolacion.factorInterpolacionLagrange(n, i, eval, x);
            System.out.print((i != 0 ? (l[i] >= 0 ? " + " : " ") : "")
                    + l[i] + "(" + fx[i] + ")");
            res += l[i] * fx[i];
        }

        System.out.println("\nResultado Lagrange en \"" + eval + "\": " + res);
    }

    public void evaluarNewton(int n, double newton[], double x[]) {
        double res = 0, factor;
        double eval = Double.parseDouble(
                JOptionPane.showInputDialog("ingrese el valor a evaluar"));

        for (int i = 0; i <= n; i++) {
            System.out.print((i != 0 ? (newton[i] >= 0 ? " + " : " ") : "") + newton[i]);
            factor = 1;
            for (int j = 0; j < i; j++) {
                factor *= eval - x[j];
                System.out.print("(" + eval + " - " + x[j] + ")");
            }
            res += newton[i] * factor;
        }
        System.out.println("\nResultado Newton en\"" + eval + "\" : " + res);

    }
}
