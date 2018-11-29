
package Interpolacion;

public class MetodosInterpolacion {

    /**Newton A factor (recursive)
     * 
     * example:
     *      f(x1)-f(x0)
     * a1= -------------
     *         x1-x0
     *              f(x2)-f(x1)         f(x1)-f(x0)
     *              -----------   -    ------------
     * a2 =            x2-x1               x1-x0
     *              -----------------------------
     *                          x2-x0
     * a3 = ...
     * @param n  <-- Equation degree
     * @param x  <-- array of x positions
     * @param fx <-- array of f(x) positions
     * @return <-- factor a in number
     */
    
    public static double factorInterpolacionNewton(int n, double []x, double []fx){
        double []resx1 = new double[n];
        double []resx2 = new double[n];
        double []resy1 = new double[n];
        double []resy2 = new double[n];
        
        if(n == 0){
            return fx[0];
        } else {
            for(int i = 0; i<n; i++){
                resx1[i] = x[i];
                resx2[i] = x[i+1];
                resy1[i] = fx[i];
                resy2[i] = fx[i+1];
            }
            
            return (factorInterpolacionNewton(n-1, resx2, resy2)
                    -factorInterpolacionNewton(n-1, resx1,resy1))
                    /(x[n]-x[0]);
        }     
    }
    
    /**Lagrange factor
     * example:
     *      (x-x1)(x-x2)(x-x3)
     * L0 = ---------------------
     *      (x0-x1)(x0-x2)(x0-x3)
     * 
     *      (x-x0)(x-x2)(x-x3)
     * L1 = ---------------------
     *      (x1-x0)(x1-x2)(x1-x3)
     * 
     * L2 = ...
     * @param n <-- Equation degree
     * @param index <-- subindex from L
     * @param x <-- value for evaluation of function
     * @param xi <-- array of x positions
     * @return <-- L factor 
     */
    public static double factorInterpolacionLagrange(
            int n,int index, double x, double []xi){
        
        double dividendo = 1;
        double divisor = 1;
        
        for(int i = 0; i <= n; i++){
            if(i != index){
                dividendo *= (x - xi[i]);
                divisor *= (xi[index] - xi[i]);
            }
        }
        
        return dividendo/divisor;        
    }
}
