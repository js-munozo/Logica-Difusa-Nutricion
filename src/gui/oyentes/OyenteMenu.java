package gui.oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OyenteMenu implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		
		if ("Salir".equals(actionCommand)) {
			
			int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir?", "¡Saliendo!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			
			if(resp == 0){	
				System.exit(0);
			}else if(resp == 1){
				
			}
		}else if("Info".equals(actionCommand)) {
			JOptionPane.showMessageDialog(null,"Desarrollado por: \n"
					+ "\nJohan Muñoz	 - js.munoz.ai@gmail.com"
					, "Acerca de nosotros"
					, JOptionPane.INFORMATION_MESSAGE);
			
		}else if("Ayuda".equals(actionCommand)) {
			JOptionPane.showMessageDialog(null,""
					+ "Esta aplicación le ayudará a conocer sobre el estado de su alimentación. "
					+ "\nCada campo debe ir con sus respectivos valores reales."
					+ "\nPor ejemplo, si se ingresan valores como números negativos o números muy grandes obtendrá"
					+ "\nun mensaje de advertencia.\n"
					+ "\nEstos son los rangos permitidos: "
					+ "\n"
					+ "\nCarbohidratos:     0 a 400 Gramos"
					+ "\nAgua:                      0 a 9 Litros"
					+ "\nGrasas:                  0 a 5 Gramos/Peso corporal"
					+ "\nProteinas:             0 a 300 Gramos"
					+ "\nMinerales:             0 a 7 (Cantidad de minerales importantes presentes en la alimentación)"
					+ "\nVitaminas:             0 a 13 (Cantidad de vitaminas presentes en la alimentación)"
					,"Ver ayuda"
					, JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
