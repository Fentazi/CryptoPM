

import java.util.Random;
import javax.swing.JOptionPane;

public class PassGen extends javax.swing.JDialog {

    public PassGen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Генератор паролей");
        setAutoRequestFocus(false);

        jSlider1.setMaximum(25);
        jSlider1.setMinimum(6);
        jSlider1.setValue(12);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel1.setText("Выбирите длинну пароля:");

        jLabel2.setText("12");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Буквы a-z");

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Цифры");

        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Буквы A-Z");

        jCheckBox4.setSelected(true);
        jCheckBox4.setText("!@#$%&");

        jButton1.setText("Генерировать");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Выбрать");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox2)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox1)
                        .addComponent(jCheckBox3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jLabel2.setText("" + jSlider1.getValue());
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (!jCheckBox1.isSelected() && !jCheckBox2.isSelected() && !jCheckBox3.isSelected() && !jCheckBox4.isSelected()) {

            JOptionPane.showMessageDialog(this, "Выберете хотя бы одну категорию символов");

        } else {
            char[] pass = new char[jSlider1.getValue()];
            Random rand = new Random();

            for (int i = 0; i < pass.length; i++) {
                int let = 0;

                while (true) {
                    let = rand.nextInt(68);
                    if (let < 10 && !jCheckBox2.isSelected()) {
                        continue;
                    }
                    if (let >= 10 && let < 36 && !jCheckBox1.isSelected()) {
                        continue;
                    }
                    if (let >= 36 && let < 62 && !jCheckBox3.isSelected()) {
                        continue;
                    }
                    if (let >= 62 && !jCheckBox4.isSelected()) {
                        continue;
                    }
                    break;
                }

                if (let < 10) {
                    pass[i] = String.valueOf(let).charAt(0);
                } else {
                    switch (let) {
                        case 10:
                            pass[i] = 'a';
                            break;

                        case 11:
                            pass[i] = 'b';
                            break;

                        case 12:
                            pass[i] = 'c';
                            break;

                        case 13:
                            pass[i] = 'd';
                            break;

                        case 14:
                            pass[i] = 'e';
                            break;

                        case 15:
                            pass[i] = 'f';
                            break;

                        case 16:
                            pass[i] = 'g';
                            break;

                        case 17:
                            pass[i] = 'h';
                            break;

                        case 18:
                            pass[i] = 'i';
                            break;

                        case 19:
                            pass[i] = 'j';
                            break;

                        case 20:
                            pass[i] = 'k';
                            break;

                        case 21:
                            pass[i] = 'l';
                            break;

                        case 22:
                            pass[i] = 'm';
                            break;

                        case 23:
                            pass[i] = 'n';
                            break;

                        case 24:
                            pass[i] = 'o';
                            break;

                        case 25:
                            pass[i] = 'p';
                            break;

                        case 26:
                            pass[i] = 'q';
                            break;

                        case 27:
                            pass[i] = 'r';
                            break;

                        case 28:
                            pass[i] = 's';
                            break;

                        case 29:
                            pass[i] = 't';
                            break;

                        case 30:
                            pass[i] = 'u';
                            break;

                        case 31:
                            pass[i] = 'v';
                            break;

                        case 32:
                            pass[i] = 'w';
                            break;

                        case 33:
                            pass[i] = 'x';
                            break;

                        case 34:
                            pass[i] = 'y';
                            break;

                        case 35:
                            pass[i] = 'z';
                            break;
                        case 36:
                            pass[i] = 'A';
                            break;

                        case 37:
                            pass[i] = 'B';
                            break;

                        case 38:
                            pass[i] = 'C';
                            break;

                        case 39:
                            pass[i] = 'D';
                            break;

                        case 40:
                            pass[i] = 'E';
                            break;

                        case 41:
                            pass[i] = 'F';
                            break;

                        case 42:
                            pass[i] = 'G';
                            break;

                        case 43:
                            pass[i] = 'H';
                            break;

                        case 44:
                            pass[i] = 'I';
                            break;

                        case 45:
                            pass[i] = 'J';
                            break;

                        case 46:
                            pass[i] = 'K';
                            break;

                        case 47:
                            pass[i] = 'L';
                            break;

                        case 48:
                            pass[i] = 'M';
                            break;

                        case 49:
                            pass[i] = 'N';
                            break;

                        case 50:
                            pass[i] = 'O';
                            break;

                        case 51:
                            pass[i] = 'P';
                            break;

                        case 52:
                            pass[i] = 'Q';
                            break;

                        case 53:
                            pass[i] = 'R';
                            break;

                        case 54:
                            pass[i] = 'S';
                            break;

                        case 55:
                            pass[i] = 'T';
                            break;

                        case 56:
                            pass[i] = 'U';
                            break;

                        case 57:
                            pass[i] = 'V';
                            break;

                        case 58:
                            pass[i] = 'W';
                            break;

                        case 59:
                            pass[i] = 'X';
                            break;

                        case 60:
                            pass[i] = 'Y';
                            break;

                        case 61:
                            pass[i] = 'Z';
                            break;
                        case 62:
                            pass[i] = '!';
                            break;

                        case 63:
                            pass[i] = '@';
                            break;

                        case 64:
                            pass[i] = '#';
                            break;

                        case 65:
                            pass[i] = '$';
                            break;

                        case 66:
                            pass[i] = '&';
                            break;

                        case 67:
                            pass[i] = '%';
                            break;
                    }

                }
            }
            jTextField1.setText(new String(pass));
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public String selectedPass;
    public boolean NoNCancle = false;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        NoNCancle = true;
        selectedPass = jTextField1.getText();
        setVisible(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
