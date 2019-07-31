

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ShowPass extends javax.swing.JFrame {

    private void ClearClipboard() {
        StringSelection stringSelection = new StringSelection("");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public ShowPass() {
        initComponents();
    }

    private class MyTableModel extends AbstractTableModel {
        

        
        @Override
        public int getRowCount() {
            return CryptoPM.database.accounts.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int c) {
            String result = "";
            switch (c) {
                case 0:
                    result = "Сайт";
                    break;
                case 1:
                    result = "Логин";
                    break;
                case 2:
                    result = "Пароль";
                    break;
                case 3:
                    result = "Коментарий";
                    break;
            }
            return result;
        }

        @Override
        public Object getValueAt(int r, int c) {
            switch (c) {
                case 0:
                    return CryptoPM.database.accounts.get(r).site;
                case 1:
                    return CryptoPM.database.accounts.get(r).login;
                case 2:
                    if (CryptoPM.database.accounts.get(r).show_pass) {
                        return CryptoPM.database.accounts.get(r).password;
                    } else {
                        return "••••••";
                    }
                case 3:
                    return CryptoPM.database.accounts.get(r).comment;
                default:
                    return "";
            }
        }
    }
    MyTableModel mtb = new MyTableModel();

    private JPopupMenu jpm;
    private JMenuItem jmi;

    public void init() {
        jTable1.setModel(mtb);
        setTitle("CryptoPM - " + CryptoPM.database.name + " / " + CryptoPM.database.file.getName());
        setLocationRelativeTo(null);
        jpm = new JPopupMenu();
        jmi = new JMenuItem("Копировать");
        jpm.add(jmi);
        jmi.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                 Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection stringSelection = new StringSelection((String) (jTable1.getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedColumn())));
                clpbrd.setContents(stringSelection, null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jPopupMenu1.setLabel("");
        jPopupMenu1.getAccessibleContext().setAccessibleParent(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/entry_new.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/entry_delete.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/entry_edit.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane2.setToolTipText("");
        jScrollPane2.setName(""); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "сайт", "логин", "пароль", "коментарий"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("сайт");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("логин");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("пароль");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("коментарий");
        }

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/show.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exit.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clear.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CryptoPM.database.accounts.get(jTable1.getSelectedRow()).revers_show();
        jTable1.updateUI();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Changed = true;
        CryptoPM.database.accounts.remove(jTable1.getSelectedRow());
        jTable1.updateUI();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Changed = true;
        CryptoPM.database.cur = new Account("", "", "", "");
        CryptoPM.database.accounts.add(CryptoPM.database.cur);
        CryptoPM.accChange.init();
        CryptoPM.accChange.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CryptoPM.database.cur = CryptoPM.database.accounts.get(jTable1.getSelectedRow());
        CryptoPM.accChange.init();
        CryptoPM.accChange.setVisible(true);
        Changed = true;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        save();
    }//GEN-LAST:event_jButton5ActionPerformed
    private void save() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            doc.appendChild(doc.createComment("TEST_STRING"));
            Element mn = doc.createElement("DataBase");
            mn.setAttribute("name", CryptoPM.database.name);
            doc.appendChild(mn);
            for (Account acc : CryptoPM.database.accounts) {
                Node root = doc.getDocumentElement();

                Element book = doc.createElement("account");
                book.setAttribute("site", acc.site);
                book.setAttribute("login", acc.login);
                book.setAttribute("pass", acc.password);
                book.setAttribute("comm", acc.comment);

                root.appendChild(book);
            }
            String xml = CryptoPM.toString(doc);
            Kuznechik Kuz = new Kuznechik();
            byte[] incr = xml.getBytes(Charset.forName("Cp1251"));

            byte[] cryptoxml = Kuz.Encript(incr, CryptoPM.database.pass.getBytes(Charset.forName("Cp1251")));
            FileOutputStream fl = new FileOutputStream(CryptoPM.database.file);

            fl.write(cryptoxml);
            fl.close();
            Changed = false;
        } catch (Exception ex) {
            Logger.getLogger(ShowPass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (Changed) {
            Object[] options = {"Сохранить и выйти", "Выйти без сохранения", "Не выходить"};
            int n = JOptionPane.showOptionDialog(this, "Есть несохраненные изменения",
                    "Подтверждение", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (n) {
                case 0:
                    save();
                    ClearClipboard();
                case 1:
                    ClearClipboard();
                    setVisible(false);
                    CryptoPM.create.setVisible(false);
                    CryptoPM.opn.setVisible(true);
                    CryptoPM.database = null;
                    System.gc();
                    break;
            }
        } else {
            ClearClipboard();
            setVisible(false);
            CryptoPM.create.setVisible(false);
            CryptoPM.opn.setVisible(true);
            CryptoPM.database = null;
            System.gc();
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    boolean Changed = false;
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (Changed) {
            Object[] options = {"Сохранить и выйти", "Выйти без сохранения", "Не выходить"};
            int n = JOptionPane.showOptionDialog(evt.getWindow(), "Есть несохраненные изменения",
                    "Подтверждение", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            switch (n) {
                case 0:
                    save();
                    ClearClipboard();
                case 1:
                    ClearClipboard();
                    evt.getWindow().setVisible(false);
                    System.exit(0);
                    break;
            }
        } else {
            ClearClipboard();
            evt.getWindow().setVisible(false);
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if(evt.isPopupTrigger()){
        jpm.show(jTable1, evt.getX(), evt.getY());}
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        ClearClipboard();
    }//GEN-LAST:event_jButton8ActionPerformed

    public void updateTable() {
        jTable1.updateUI();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
