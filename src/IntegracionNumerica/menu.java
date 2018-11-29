/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegracionNumerica;

import javax.swing.JOptionPane;

public class menu {
    
    private double datos[];
    private double dx;
    
    public static void main(String[] args) {
        menu op = new menu();
        
        int opcion;
        
        do{
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese una opcion\n"
                                                                + "1-ingresar datos\n"
                                                                + "2-Metodo trapezoidal\n"
                                                                + "3-parbolico"));
            op.opciones(opcion);
        }while(opcion !=3);
    }
    
    public void opciones(int opcion){
        double resultado;
        switch(opcion){
            case 1:
                ingresar();
                resultado = 0;
                break;
            case 2: 
                resultado = MetodosIntegracion.trapezoidal(datos, dx);
                System.out.println(resultado);
                break;
                
            case 3:
                resultado = MetodosIntegracion.parabolico(datos, dx);
                System.out.println(resultado);
                break;
            default: resultado = 0;
        }
    }
    
    public void ingresar(){
        double a,b;
        int n;
        
        
        a = Double.parseDouble(JOptionPane.showInputDialog("ingrese el limite inferior"));
        b = Double.parseDouble(JOptionPane.showInputDialog("ingrese el limite superior"));
        n = Integer.parseInt(JOptionPane.showInputDialog("ingrese el numero de iteraciones")); 
        dx = (b-a)/n;
        
        datos = new double[n+1];
        
        for(int i = 0; i <= n; i++){
            datos[i] = funcion(a+(i*dx));
        }
    }
    
    public double funcion(double x){
        return (2 * Math.pow(x,2) - 4*x + 5);
    }
}
