

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenDataBase extends javax.swing.JFrame {

    String path = "";

    public OpenDataBase() {
        initComponents();
        path = CryptoPM.prefs.get(CryptoPM.PREF_NAME, "C:\\");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CryptoPM");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Открыть существующую базу данных или создать новую?");
        jLabel1.setAlignmentX(0.5F);

        jButton1.setText("Создать");
        jButton1.setDefaultCapable(false);
        jButton1.setMaximumSize(new java.awt.Dimension(79, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(79, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(79, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Открыть");
        jButton2.setDefaultCapable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        CryptoPM.create.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    int exit_status = 0;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            JFileChooser fileopen = new JFileChooser();
            if (new File(path).exists()) {
                fileopen.setSelectedFile(new File(path));
            }
            fileopen.setFileFilter(new FileNameExtensionFilter("Файл базы данных", "JWPdb"));
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                CryptoPM.prefs.put(CryptoPM.PREF_NAME, file.getAbsolutePath());
                JDialog dialog = new JDialog(this, "Введите мастер-пароль", true);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                JPanel jp = new JPanel();
                JPasswordField jpf = new JPasswordField(20);
                JButton jb = new JButton("Открыть");
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exit_status = 1;
                        dialog.setVisible(false);
                    }
                });

                jp.add(jpf);
                jp.add(jb);
                dialog.setContentPane(jp);
                dialog.pack();
                while (true) {
                    exit_status = 0;
                    dialog.setVisible(true);
                    if (exit_status == 1) {
                        String pass = new String(jpf.getPassword());

                        Kuznechik Kuz = new Kuznechik();
                        FileInputStream fin = new FileInputStream(file);
                        byte[] enc = new byte[fin.available()];
                        fin.read(enc);
                        fin.close();
                        byte[] DecryptedFile = Kuz.Decript(enc, new String(pass).getBytes(Charset.forName("Cp1251")));

                        String DecryptedText = new String(DecryptedFile, Charset.forName("Cp1251"));
                        //   System.out.println("Расшифрованный текст: " + DecryptedText);
                        if (DecryptedText.contains("<!--TEST_STRING-->")) {
                            CryptoPM.database = new DataBase(DecryptedText);
                            CryptoPM.database.pass = pass;
                            CryptoPM.database.file = file;
                            setVisible(false);
                            CryptoPM.create.setVisible(false);
                            CryptoPM.shwpss.setVisible(true);
                            CryptoPM.shwpss.init();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(this, "Мастер-пароль неверный");
                        }
                    } else {
                        break;
                    }
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
