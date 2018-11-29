/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasEcuaciones;

import javax.swing.JOptionPane;


public class GaussSeidel {
    
    public static void main(String[] args) {
        GaussSeidel j = new GaussSeidel();
        int tam;
        double error;
        
        tam = Integer.parseInt(JOptionPane.showInputDialog("ingrese el numero de ecuaciones"));
        error = Double.parseDouble(JOptionPane.showInputDialog("ingrese el error en %"));
        double m[][] = new double[tam][tam+1];
        
        j.ingreso(m, tam, tam+1);
        if(j.dominante(m))
            j.calculo(tam, m,error );
        else
            System.out.println("La matriz no es dominante");
    }
    
    public void ingreso(double m[][],int filas, int col){
        for(int i = 0; i < filas; i ++ ){
            for(int j = 0; j < col;j++){
                m[i][j] = Double.parseDouble(JOptionPane.showInputDialog(
                        j < filas ? "ingrese x" + j : "ingrese la constante"));
            }
        }
    }
    
    public boolean dominante(double m[][]){
        int suma;
        for (int i = 0; i < m.length; i++) {
            suma = 0;
            for (int j = 0; j < m[0].length; j++) {
                if(i != j)
                    suma += Math.abs(m[i][j]);
            }
            if(m[i][i] < suma )
                return false;
        }
        return true;
    }
    
    public double[] calculo(int tam, double m[][], double error){
        double d[]= new double [tam];
        double aux[] = new double [tam];
        double ea[] = new double [tam];
        
        double suma ;
        boolean condicion = true;
        for (int i = 0; condicion ; i++ ){
            for(int j = 0; j< tam; j++){
                suma = 0;
                for(int k = 0; k < tam; k++){
                    if(k != j)
                        suma += m[j][k] * d[k];
                }
                suma *= -1;
                suma += m[j][tam];
                suma /= m[j][j];
                d[j] = suma;
                ea[j] = Math.abs((d[j]-aux[j])/d[j])*100;
                
            }
            for(int j = 0;j < tam; j++){
                if(ea[j] < error ){
                    condicion = false;
                }else{
                    condicion = true;
                    break;
                }
            }
            System.out.println("iteración: "+i);
            System.out.println("valor    |    error");
            aux = d.clone();
            imprimir(d,tam,ea);
            
        }
        
        
        return d;
    }
    
    public void imprimir(double []d, int tam, double[] ea){
        for(int i = 0; i< tam; i ++){
            System.out.printf("%.5f | %.5f \n",d[i],ea[i]);
        }
        
    }
}
