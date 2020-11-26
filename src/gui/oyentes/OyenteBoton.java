package gui.oyentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class OyenteBoton implements ActionListener{

	JTextField T1;
	JTextField T2;
	JTextField T3;
	JTextField T4;
	JTextField T5;
	JTextField T6;
	JTextArea TA1;
	
	public OyenteBoton(JTextField t1, JTextField t2, JTextField t3,JTextField t4, JTextField t5, JTextField t6, JTextArea ta1) {
		// TODO Auto-generated constructor stub
		T1 = t1;
		T2 = t2;
		T3 = t3;
		T4 = t4;
		T5 = t5;
		T6 = t6;
		TA1 = ta1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (e.getSource() instanceof JButton) {
			if("Enviar".equals(actionCommand)) {
				try {
					FIS nutricion = FIS.load("src/fuzzy/nutria.fcl", true);
					
					// Set inputs
					String errors = "";
					
					if(Double.parseDouble(T1.getText()) > 400 || Double.parseDouble(T1.getText()) < 0) {
						errors += "Ingrese un valor correcto para 'Carbohidratos'...\n"; 
					}
					if(Double.parseDouble(T2.getText()) > 15 || Double.parseDouble(T1.getText()) < 0) {
						errors += "Ingrese un valor correcto para 'Aguas'...\n"; 
					}
					if(Double.parseDouble(T3.getText()) > 10 || Double.parseDouble(T1.getText()) < 0) {
						errors += "Ingrese un valor correcto para 'Grasas'...\n"; 
					}
					if(Double.parseDouble(T4.getText()) > 300 || Double.parseDouble(T1.getText()) < 0) {
						errors +=  "Ingrese un valor correcto para 'Proteinas'...\n"; 
					}
					if(Double.parseDouble(T5.getText()) > 10 || Double.parseDouble(T1.getText()) < 0) {
						errors += "Ingrese un valor correcto para 'Minerales'...\n"; 
					}
					if(Double.parseDouble(T6.getText()) > 15 || Double.parseDouble(T1.getText()) < 0) {
						errors += "Ingrese un valor correcto para 'Vitaminas'...\n"; 
					}
					if(errors != "") {
						throw new ArithmeticException(errors);
					}
					
					nutricion.setVariable("carbohidratos", Double.parseDouble(T1.getText()));
					nutricion.setVariable("agua", Double.parseDouble(T2.getText()));
					nutricion.setVariable("grasas", Double.parseDouble(T3.getText()));
					nutricion.setVariable("proteinas", Double.parseDouble(T4.getText()));
					nutricion.setVariable("minerales", Double.parseDouble(T5.getText()));
					nutricion.setVariable("vitaminas", Double.parseDouble(T6.getText()));
					
			        // Evaluate
					nutricion.evaluate(); 

			        //Graficas de conjuntos difusos
			        JFuzzyChart.get().chart(nutricion.getFunctionBlock("nutria"));

			        // Show output variable
			        TA1.setText("Tu nutrición tiene un valor de: " + nutricion.getVariable("nutricion").defuzzify());		        
			        TA1.setText(TA1.getText() + "\n \n" + "El metodo usado para 'defuzzificar' el estado nutricional fue: " + nutricion.getVariable("nutricion").getDefuzzifier());
			        TA1.setText(TA1.getText() + "\n \n" + "El grado de pertenencia a los conjuntos difusos es: ");
			        TA1.setText(TA1.getText() + "\n" + "	'muy mal': " + nutricion.getVariable("nutricion").getMembership("muy_mal"));
			        TA1.setText(TA1.getText() + "\n" + "	'mal': " + nutricion.getVariable("nutricion").getMembership("mal"));
			        TA1.setText(TA1.getText() + "\n" + "	'regular': " + nutricion.getVariable("nutricion").getMembership("regular"));
			        TA1.setText(TA1.getText() + "\n" + "	'buena': " + nutricion.getVariable("nutricion").getMembership("buena"));
			        TA1.setText(TA1.getText() + "\n" + "	'excelente': " + nutricion.getVariable("nutricion").getMembership("excelente"));
			        TA1.setText(TA1.getText() + "\n \n" + "El valor nutricional es una medida de 0 a 100, \n"
			        									+ "que dice aproximadamente que tan bien te estas alimentando \n "
			        									+ "Siendo 0 muy mal y 100 muy bien.");
			        TA1.setEditable(false);
				}
				
				catch(ArithmeticException ex) {
					TA1.setText(ex.getMessage());
					TA1.setText(TA1.getText() + "\nQue cumplan los intervalos.");
					TA1.setText(TA1.getText() + "\nPuede ir a la sección de ayuda para mas información.");
				}
		        catch(NumberFormatException  ex) {
		        	TA1.setText("Por favor rellene todos los campos correctamente...");
		        	TA1.setText(TA1.getText() + "\nPuede ir a la sección de ayuda para mas información.");
		        }
				catch (NullPointerException ex) {
					TA1.setText("No es posible graficar en este momento...");
				}
				catch(Exception  ex) {
		        	TA1.setText("Ha ocurrido un error..." + "\n" + ex.getMessage());
		        }
			}
		}
	}
}
