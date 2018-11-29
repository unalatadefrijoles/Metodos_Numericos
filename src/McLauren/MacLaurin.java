/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package McLauren;

import java.util.Scanner;

/**
 *
 * @author jacob
 */
public class MacLaurin {
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jacob
 */
    //public static final double DX = 0.01;
    public static final int OPCION_SENO = 1;
    public static final int OPCION_COSENO = 2;
    public static final int OPCION_EXPONENCIAL = 3;
    public static final String[] ARRAY_SENO = {"Sen(x)", " + Cos(x)", " - Sen(x)", " - Cos(x)"};
    public static final String[] ARRAY_COSENO = {"Cos(x)", " - Sen(x)", " - Cos(x)", " + Sen(x)"};
    public static final String[] ARRAY_SENO_VALUE = {"Sen(0)", " + Cos(0)", " - Sen(0)", " - Cos(0)"};
    public static final String[] ARRAY_COSENO_VALUE = {"Cos(0)", " - Sen(0)", " - Cos(0)", " + Sen(0)"};
    public static final int[] SENO = {0, 1, 0, -1};
    public static final int[] COSENO = {1, 0, -1, 0};
    private String polinomioSinEvaluar, polinomioSustituido, polinomioEvaluado;
    private double resultadoEvaluado;
    private double resultadoReal;

    public static void main(String[] args) {
       
        MacLaurin serie = new MacLaurin();
        Scanner scanner = new Scanner(System.in);
        //Para evaluar las funciones calc 0.5 c
        double x;
        // n iteraciones (EditText) y opcion Buttons
        int n, opcion;

        String msg = "Escribe el numero de la expansión: )";

        System.out.println(msg);
        n = scanner.nextInt();

        System.out.println("Seleccione la funcion\n"
                + "\n1-Seno"
                + "\n2-Coseno"
                + "\n3-Exponencial");
        opcion = scanner.nextInt();

        System.out.println("Valor de evaluacion");
        x = scanner.nextDouble();

        serie.calculos(x, n, opcion);
        serie.showMsg();
    }
    
    public void showMsg(){
        String msgp,msgrr,msgre;

        msgp ="";  msgrr="";  msgre="";
        msgp += "Resultado del polinomio: ";
        System.out.println(msgp + getPolinomioEvaluado());
        
        msgre += "Resultado aproximado: ";
        System.out.println(msgre + getResultadoEvaluado());
        
        msgrr += "Valor real: ";
        System.out.println(msgrr + getResultadoReal());

    }
    
    public MacLaurin() {
        polinomioSinEvaluar = "";
        polinomioSustituido = "";
        polinomioEvaluado = "";
        resultadoEvaluado = 0;
    }

    /**
     * Metodo para el calculo de la serie de taylor
     *
     * @param evaluar es el valor evaluado en la serie
     * @param n es el grado de expansión de la serie
     * @return --> el String con el resultado reducido
     */
    public MacLaurin calculos(double evaluar, int n, int opcion) {
        int fac;
        for (int i = 0; i <= n; i++) {
            fac = factorial(i);
            resultados(fac, evaluar, i, opcion);
        }
        return this;
    }

    /*
    //n -> es el grado de expansión
    public double derivada(double x, int n){
        if( n == 0) {
            return funcion(x);
        } else{
            return (derivada(x + DX, n - 1) - derivada(x, n - 1)) / DX;
        }
    }
    public double funcion(double x){
        return  Math.cos(x);
    }
     */
    public int factorial(int x) {
        if (x == 0) {
            return 1;
        } else {
            return x * factorial(x - 1);
        }
    }

    public void resultados(int fac, double evaluar, int i, int opcion) {
        switch (opcion) {
            case OPCION_SENO:
                seno(fac, evaluar, i, opcion);
                break;
            case OPCION_COSENO:
                coseno(fac, evaluar, i, opcion);
                break;
            case OPCION_EXPONENCIAL:
                e(fac, evaluar, i, opcion);
                break;
            default:
        }
        polinomioSinEvaluar += "(x^" + i + ")/" + fac + "  ";
        polinomioEvaluado += "(x^" + i + ")/" + fac + "  ";
        polinomioSustituido += "(x^" + i + ")/" + fac + "  ";
    }

    public void e(int fac, double evaluar, int i, int opcion) {
        polinomioSinEvaluar += "e^x";
        polinomioSustituido += "e^0";
        polinomioEvaluado += String.valueOf(1);
        resultadoEvaluado += 1 * Math.pow(evaluar, i) / fac;
        resultadoReal = Math.pow(Math.E, evaluar);
    }

    public void coseno(int fac, double evaluar, int i, int opcion) {
        polinomioSinEvaluar += ARRAY_COSENO[i % 4];
        polinomioSustituido += ARRAY_COSENO_VALUE[i % 4];
        polinomioEvaluado += String.valueOf(COSENO[i % 4]);
        resultadoEvaluado += COSENO[i % 4] * Math.pow(evaluar, i) / fac;
        resultadoReal = Math.cos(evaluar);
    }

    public void seno(int fac, double evaluar, int i, int opcion) {
        polinomioSinEvaluar += ARRAY_SENO[i % 4];
        polinomioSustituido += ARRAY_SENO_VALUE[i % 4];
        polinomioEvaluado += String.valueOf(SENO[i % 4]);
        resultadoEvaluado += SENO[i % 4] * Math.pow(evaluar, i) / fac;
        System.out.println(resultadoEvaluado);
        resultadoReal = Math.sin(evaluar);
    }

    public String getPolinomioSinEvaluar() {  return polinomioSinEvaluar;  }
    public String getPolinomioSustituido() {  return polinomioSustituido;  }
    public String getPolinomioEvaluado() {  return polinomioEvaluado;  }
    public double getResultadoEvaluado() {  return resultadoEvaluado;  }
    public double getResultadoReal() {  return resultadoReal;  }
}