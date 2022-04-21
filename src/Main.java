import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Equation {
    private double ax;
    private double by;
    private double c;

    public Equation(double ax, double by, double c) {
        this.ax = ax;
        this.by = by;
        this.c = - c;
    }

    public ArrayList<Double> yEquation(){
        ArrayList<Double> result = new ArrayList<>();
        result.add(- c / by);
        result.add(- ax / by);
        return result;
    }

    public double xComputation(ArrayList<Double> yEquationResult){
        /*
        * metodo più leggibile
        * double result1 = by * yEquationResult.get(0);
        * double result2 = by * yEquationResult.get(1);
        * double partial1 = ax + result2;
        * double partial2 = result1 + c;
        * return - partial2 / partial1;
        * */
        return - (((by * yEquationResult.get(0)) + c) / ((by * yEquationResult.get(1)) + ax));
    }
    public double yComputation (double xValue){
        return - (c + (ax * xValue)) / by;
    }
}

public class Main {
    static ArrayList<JTextField> txt;
    static ArrayList<JButton> btn;

    static JPanel getInputPanel(){
        JLabel parentesi = new JLabel("{  ");
        parentesi.setFont(new Font("Arial", Font.PLAIN, 30));
        JPanel input = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        input.setLayout(gridBagLayout);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        input.add(parentesi,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(0),gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(new JLabel("  X +   "),gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(1),gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(new JLabel("  Y =   "),gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(2),gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(3),gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(new JLabel("  X +  "),gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(4),gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 0;
        gbc.gridwidth = 1;
        input.add(new JLabel("  Y =   "),gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(5),gbc);
        return input;
    }
    static JPanel getOutputPanel(){
        JLabel parentesi = new JLabel("{ ");
        parentesi.setFont(new Font("Arial", Font.PLAIN, 30));
        JPanel input = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        input.setLayout(gridBagLayout);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        input.add(parentesi,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(new JLabel("  X =   "),gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(6),gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(new JLabel("  Y =   "),gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        input.add(txt.get(7),gbc);

        return input;
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("LINEAR SYSTEM");
        mainFrame.setSize(500,300);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        //creazione area di testo
        txt = new ArrayList<>();
        for(int i = 0; i < 8; i++)
            txt.add(new JTextField(5));
        //creazione bottoni
        btn = new ArrayList<>();
        btn.add(new JButton("CALCOLA"));
        btn.add(new JButton("CANCELLA"));
        //creazione pannelli principali
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body,BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(btn.get(0));
        buttons.add(btn.get(1));

        JPanel input = getInputPanel();
        JPanel output = getOutputPanel();

        //actionlistener
        btn.get(0).addActionListener(e -> {
            Equation e1 = new Equation(Double.parseDouble(txt.get(0).getText()),Double.parseDouble(txt.get(1).getText()),Double.parseDouble(txt.get(2).getText()));
            Equation e2 = new Equation(Double.parseDouble(txt.get(3).getText()),Double.parseDouble(txt.get(4).getText()),Double.parseDouble(txt.get(5).getText()));
            ArrayList<Double> partial = e1.yEquation();
            double x =Math.round(e2.xComputation(partial)*100.0)/100.0;
            double y = Math.round(e2.yComputation(x)*100.0)/100.0;
            txt.get(6).setText(Double.toString(x));
            txt.get(7).setText(Double.toString(y));
        });
        btn.get(1).addActionListener(e -> {
            for(JTextField t : txt)
                t.setText("");
        });

        body.add(input);
        body.add(buttons);
        body.add(output);
        mainFrame.getContentPane().add(BorderLayout.CENTER,body);
        mainFrame.setVisible(true);
    }
}
