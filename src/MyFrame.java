
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class MyFrame extends JFrame {
    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result;

    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();

    JLabel brandL = new JLabel("Марка:");
    JLabel modelL = new JLabel("Модел:");
    JLabel priceL = new JLabel("Цена:");
    JLabel YearOfManufactureL = new JLabel("Година на производство:");
    JLabel engineL = new JLabel("Тип двигател:");

    JTextField brandTF = new JTextField();
    JTextField modelTF = new JTextField();
    JTextField priceTF = new JTextField();
    JTextField YearOfManufactureTF = new JTextField();

    String[] item = {"Бензинов", "Дизелов", "Газ/Безнзин", "Електрически", "Хибриден"};
    JComboBox<String> engineCombo = new JComboBox<String>(item);

    JButton addBt = new JButton("Добавяне");
    JButton deleteBt = new JButton("Изтриване");
    JButton editBt = new JButton("Редактиране");
    //downPanel
    JTable table = new JTable();
    JScrollPane myScroll = new JScrollPane(table);

    public MyFrame() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 1));

        upPanel.setLayout(new GridLayout(5, 2));

        upPanel.add(brandL);
        upPanel.add(brandTF);
        upPanel.add(modelL);
        upPanel.add(modelTF);
        upPanel.add(priceL);
        upPanel.add(priceTF);
        upPanel.add(YearOfManufactureL);
        upPanel.add(YearOfManufactureTF);
        upPanel.add(engineL);
        upPanel.add(engineCombo);

        this.add(upPanel);

        midPanel.add(addBt);
        midPanel.add(deleteBt);
        midPanel.add(editBt);

        this.add(midPanel);
        //down Panel
        myScroll.setPreferredSize(new Dimension(350,150));
        downPanel.add(myScroll);
        this.add(downPanel);

        addBt.addActionListener(new AddAction());
        refreshTable();
        this.setVisible(true);
    }//Еnd of the constructor
    public void refreshTable(){
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("select * from cara");
            result = state.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class AddAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "insert into cara values(null, ?, ?, ?, ?, ?)";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, brandTF.getText());
                state.setString(2, modelTF.getText());
                state.setString(3, engineCombo.getSelectedItem().toString());
                state.setInt(3, Integer.parseInt(priceTF.getText()));
                state.setInt(4, Integer.parseInt(YearOfManufactureTF.getText()));


                state.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println(brandTF.getText() + " " + modelTF.getText() + " " + engineCombo.getSelectedItem()
                    + " " + YearOfManufactureL.getText());
        }
    }
}

