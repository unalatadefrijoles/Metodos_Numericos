package metodosnumericos;

import javax.swing.JOptionPane;

public class IMC {
    public static void main(String[] args) {
        float peso,altura,imc;
        IMC i = new IMC();
        
        peso = Float.parseFloat(JOptionPane
                .showInputDialog("Ingrese su peso(kg)"));
        altura = Float.parseFloat(JOptionPane
                .showInputDialog("Ingrese su altura(metros)"));
        
        imc = (float) (peso / Math.pow(altura, 2));
        
        i.opciones(imc);
        
    }
    
    private void opciones(float imc){
        String estadoSalud = "";
        
        if(imc < 18.5)
            estadoSalud = "Usted es peso bajo";
        else if(imc >=18.5 && imc <25)
            estadoSalud = "Usted es peso normal";
        else if(imc >= 25 && imc < 30)
            estadoSalud = "Usted esta en Sobrepeso";
        else if(imc >= 30)
            estadoSalud = "Obesidad";
          
        JOptionPane.showMessageDialog(null, "Su IMC: "
                    + imc + "\n" + estadoSalud);
    }
}
