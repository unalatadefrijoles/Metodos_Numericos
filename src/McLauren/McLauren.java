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
public class McLauren {
    
    public static final double DX = 0.01;
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        double x = 0;
        int n;
        
        String msg ="Escribe el numero de la expansión ;)";
        
        System.out.println(msg);
        n = scanner.nextInt();
        
        System.out.println("Resultado: " + new McLauren().calculos(x,n));
        
    }
    /**
     * Metodo para el calculo de la serie de taylor
     * @param x es el valor evaluado en la serie
     * @param n es el grado de expansión de la serie
     * @return --> el String con el resultado reducido
     */
    public String calculos(double x, int n){
        String r = "";
        int fac;
        double fun;
        
        for (int i = 0; i <= n; i++) {
            fac = factorial(i);
            fun = Math.round(derivada(x, i));
            if( fun != 0){
                r = resultados(r, fac, fun, i);
            }
        }
        return r;
    }
    
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
    
    public int factorial(int x){
        if( x == 0 )
            return 1;
        else
            return x * factorial(x - 1);
    }
    
    public String resultados (String r, int fac, double fun, int i){        
        //maneja los signos del resultado
        r += (r.isEmpty()? "" :(fun > 0 ? " + " : " - ") );
        //modifica el valor de fun para ser positivo
        fun = (fun > 0 ? fun : fun * -1);
        //anexa el resultado, en caso de que x sea elevada a 0, no se concatena
        r += "(" + fun + (i != 0 ? "(x^" + i + ")" : "" )+"/" + fac + ")";
        
        return r;
    }
    
    
}