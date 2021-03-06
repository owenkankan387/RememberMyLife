/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remmylife.gui;

import com.remmylife.database.DiaryManager;
import com.remmylife.diary.TextDiary;
import com.remmylife.head.DiaryType;

import java.awt.Component;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

/**
 *
 * @author bestofme
 */
public class TextDiaryDialog extends javax.swing.JDialog {

    /**
     * Creates new form NewTextDiaryDialog
     */
    public TextDiaryDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       
        this.setTitle("New Text Diary");
        jTextField1.setText("New Diary");
        dateCreated = new Date();
        
        System.out.println(dateCreated);
        
        initPopUpMenus();
    }
    
    public TextDiaryDialog(java.awt.Frame parent, boolean modal, TextDiary diary) {
        super(parent, modal);
        initComponents();
        
        setTitle(diary.getTitle());
        
        jTextField1.setText(diary.getTitle());
        jTextArea1.setText(diary.getText());
        
        textDiary = diary;
        
        initPopUpMenus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Text Diary");
        setLocationByPlatform(true);
        setModal(true);
        setResizable(false);

        jLabel1.setText("Title :");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // For Save Button
        
        UICommonData.Instance().diaryDialogExitStatus = UICommonData.ON_SAVE;
       
        
        if (textDiary == null) {
            textDiary = generateDiary();
            UICommonData.Instance().newAddedDiary = textDiary;
            try {
				textDiary.setId(UICommonData.Instance().diaryManager.addDiary(textDiary));
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else {
            localUpdateDiary(textDiary);
            try {
				UICommonData.Instance().diaryManager.updateDiary(textDiary);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private TextDiary generateDiary() {   
        TextDiary diary = new TextDiary();
        diary.setDate(dateCreated);
        localUpdateDiary(diary);
        return diary;
    }
    
    private void localUpdateDiary(TextDiary diary) {
        diary.setTitle(jTextField1.getText());
        diary.setType(DiaryType.TEXT_DIARY);
        diary.setText(jTextArea1.getText());
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // For Cancel Button
        UICommonData.Instance().diaryDialogExitStatus = UICommonData.ON_CANCEL;
        
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
        
    private void initPopUpMenus() {
        
        contextMenu1.add(cut1 = new JMenuItem("cut"));
	contextMenu1.add(copy1 = new JMenuItem("copy"));
	contextMenu1.add(paste1 = new JMenuItem("paste"));
	cut1.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
	copy1.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
        paste1.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		    
	contextMenu2.add(cut2 = new JMenuItem("cut"));
	contextMenu2.add(copy2 = new JMenuItem("copy"));
	contextMenu2.add(paste2 = new JMenuItem("paste"));
	cut2.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
	copy2.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
	paste2.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		    
        cut1.addActionListener(actionListenerForTitleFeild);
        copy1.addActionListener(actionListenerForTitleFeild);
        paste1.addActionListener(actionListenerForTitleFeild);
	cut2.addActionListener(actionListenerForDiaryArea);
	copy2.addActionListener(actionListenerForDiaryArea);
	paste2.addActionListener(actionListenerForDiaryArea);
		    
        jTextField1.add(contextMenu1);
        jTextField1.addMouseListener(mouseListener1);
        jTextArea1.add(contextMenu2);
        jTextArea1.addMouseListener(mouseListener2);
    }
		
    private ActionListener actionListenerForTitleFeild  = new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent e) {			
            performAction(e, jTextField1);
	}
    };
		
    private ActionListener actionListenerForDiaryArea  = new ActionListener() {
			
	@Override
	public void actionPerformed(ActionEvent e) {
            performAction(e, jTextArea1);
	}
    };
			
    private MouseListener mouseListener1 = new MouseListener() {
			
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mousePressed(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) {	}
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
		copy1.setEnabled(canCopy(e.getComponent()));
		paste1.setEnabled(isStringOnClipboard());
                cut1.setEnabled(canCopy(e.getComponent()));
		contextMenu1.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    };
    
    private MouseListener mouseListener2 = new MouseListener() {
			
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mousePressed(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) {	}
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
		copy2.setEnabled(canCopy(e.getComponent()));
		paste2.setEnabled(isStringOnClipboard());
                cut2.setEnabled(canCopy(e.getComponent()));
		contextMenu2.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    };	
		
    private boolean isStringOnClipboard() {
	boolean b = false;
	Clipboard clipboard = this.getToolkit().getSystemClipboard();
	Transferable content = clipboard.getContents(this);
	try {
            if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
		b = true;
            }	
	} catch (Exception e) {
			  		
	}
	return b;
    }
		
    private boolean canCopy(Component component) {
	boolean b = false;
	int start = ((JTextComponent)component).getSelectionStart();
	int end = ((JTextComponent)component).getSelectionEnd();
	if (start != end)
            b = true;
	return b;
    }
		
    private void performAction(ActionEvent e, JTextComponent textComponent) {
	String command = e.getActionCommand();
	if (command.equals("cut")) {
            textComponent.cut();
	} else if (command.equals("copy")) {
            textComponent.copy();
	} else if (command.equals("paste")) {
            textComponent.paste();
	}
    }
                    
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NewTextDiaryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NewTextDiaryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NewTextDiaryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NewTextDiaryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                TextDiaryDialog dialog = new TextDiaryDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables


    private JPopupMenu contextMenu1 = new JPopupMenu();
    private JPopupMenu contextMenu2 = new JPopupMenu();
    private JMenuItem cut1 = null, copy1 = null, paste1 = null;		
    private JMenuItem cut2 = null, copy2 = null, paste2 = null; 
    private TextDiary textDiary = null;
    private Date dateCreated = null;
}
