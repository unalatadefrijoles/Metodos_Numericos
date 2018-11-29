/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegracionNumerica;


public class MetodosIntegracion {

    public static double trapezoidal(double [] datos, double dx){
        double suma = 0;
        for(int i=1; i < (datos.length - 1) ; i++){
            suma += datos[i];
        }
        suma *= 2;
        suma += datos[0] + datos[datos.length-1]; 
        suma *= (dx/2);
        return suma;
    }     
    
    public static double parabolico(double [] datos,double dx){
        double suma = 0;
        double res;
        for (int i = 1; i < (datos.length-1); i+=2){
            suma +=datos[i];
        }
        res = suma*4;
        suma = 0;
        for(int i = 2; i < (datos.length-1); i+=2){
            suma += datos[i];
        }
        res += suma*2;
        res += datos[0] + datos[datos.length - 1];
        res *= dx/3;
        return res;
    }
}
