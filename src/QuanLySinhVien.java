import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuanLySinhVien extends JFrame {
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField genderTextField;
    private JTextField ageTextField;
    private JButton exitButton;
    private JButton addButton;
    private JTable studentTable;
    private JPanel mainPanel;

    private ArrayList<Sinh_Vien> list = new ArrayList<>(); // List to store student data
    private DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Name", "Age", "Gender"},
            0
    );

    private void createTable() {
        for (Sinh_Vien sinhVien : list) {
            String ID = sinhVien.getID();
            String Name = sinhVien.getName();
            String Gender = sinhVien.getGender();
            int Age = sinhVien.getAge();
            Object[] data = {ID, Name, Age, Gender};

            model.addRow(data);
        }
        studentTable.setModel(model);
    }

    private boolean isDuplicateMSV(String Id) {
        for (Sinh_Vien sv : list) {
            if (sv.getID().equals(Id)) {
                return true; // Found a duplicate MSV
            }
        }
        return false; // No duplicate MSV found
    }

    public QuanLySinhVien() {

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();
                String name = nameTextField.getText();
                String gender = genderTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                try {
                    //Add the inputs into table
                    // int i = 0; // Row number
                    Object[] rowData = {id, name, gender, age};

                    boolean valid = true;
                    // Kiểm tra độ dài của ID có bằng 9 không.
                    if (id.length() != 9) {
                        JOptionPane.showMessageDialog(null, "ID must contain 9 characters!");
                        valid = true;
                    }

                    // Không add ID trùng nhau
                    if (isDuplicateMSV(id)) {
                        JOptionPane.showMessageDialog(null, "Duplicate ID found!");
                        valid = true;
                    }

                    // Kiểm tra ID có ký tự là chữ không (ID phải là số)
                    for (int i = 0; i < idTextField.getText().length(); i++) {
                        char c = idTextField.getText().charAt(i);
                        if (Character.isDigit(c) == false) {
                            JOptionPane.showMessageDialog(null, "MSV must be all number!");
                            valid = false;
                            break;
                        }
                    }

                    //Tên không được để trống
                    if (nameTextField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Must include name!");
                        valid = false;
                    }

                    // Kiểm tra tuổi xem có hợp lệ không
                    if (age < 9 || age > 80) {
                        JOptionPane.showMessageDialog(null, "Invalid age found!");
                        valid = false;
                    }

                    if (valid == true) {
                        model.addRow(rowData);
                        createTable();
                        idTextField.setText("");
                        nameTextField.setText("");
                        genderTextField.setText("");
                        ageTextField.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid inputs!");
                        nameTextField.requestFocusInWindow();
                    }

                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Must be an integer!");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        QuanLySinhVien qlsv = new QuanLySinhVien();
        qlsv.setTitle("This is Viet Anh's Program!");
        qlsv.setContentPane(qlsv.mainPanel);
        qlsv.setVisible(true);
        qlsv.setDefaultCloseOperation(EXIT_ON_CLOSE);
        qlsv.setSize(500, 500);

    }
}
