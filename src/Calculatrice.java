import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice extends JFrame {
    private double num1;
    private String operateur;
    private JTextField textField;
    public Calculatrice(){
        //creation de la fenetre
        setTitle("calculatrice");
        setSize(330,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane() , BoxLayout.Y_AXIS));

        //txt field
        JPanel p = new JPanel();
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 80));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        p.setBorder(new EmptyBorder(10,10,10,10));
        p.add(textField);

        //clavier
        JPanel c = new JPanel();
        c.setLayout(new GridLayout(5,4,5,5));
        c.setBorder(new EmptyBorder(10,10,10,10));
        c.setPreferredSize(new Dimension(300, 280));

        // Liste des boutons
        String[] boutons = {
                "C", "", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "" ,"0", "", "=",
        };

        for (String b : boutons) {
            JButton btn = new JButton(b);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    boutonClique(b);
                }
            });
            c.add(btn);
        }

        //ajouter les panel a la JFrame
        add(p);
        add(c);

        //rendre la fenetre visible
        setVisible(true);
    }
    private void boutonClique(String boutonTexte) {
        switch (boutonTexte) {
            case "C":
                textField.setText("");
                num1 = 0;
                operateur = "";
                break;
            case "+": case "-": case "x": case "/":
                operateur = boutonTexte;
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                break;
            case "%":
                if (!textField.getText().isEmpty()) {
                    double val = Double.parseDouble(textField.getText());
                    textField.setText(String.valueOf(val / 100));
                }
                break;
            case "=":
                double num2 = Double.parseDouble(textField.getText());
                double result = 0;
                switch (operateur) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "x": result = num1 * num2; break;
                    case "/":
                        if (num2 != 0) result = num1 / num2;
                        else textField.setText("Erreur");
                        break;
                }
                if (!textField.getText().equals("Erreur")) {
                    textField.setText(String.valueOf(result));
                }
                break;
            case "":
                break;
            default:
                textField.setText(textField.getText() + boutonTexte);
        }
    }
}
