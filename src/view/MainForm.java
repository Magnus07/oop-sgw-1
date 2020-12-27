package view;

import com.sun.tools.javac.Main;
import model.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Enumeration;

public class MainForm {
    static JFrame frame = (JFrame) getFrame();

    private static Object getFrame() {
        JFrame jframe  = new JFrame();
        return jframe;
    }

    private JTree tree1;
    private JButton addButton;
    private JButton eraseButton;
    private JButton editButton;
    private JButton storeButton;
    private JButton restoreButton;
    private JButton countLiteratureByYearButton;
    private JPanel Pane;
    private JButton findLitByPublisherButton;

    public MainForm() {
        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Clicked(e);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onErase();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEdit();
            }
        });
        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStore();
            }
        });
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRestore();
            }
        });
        countLiteratureByYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getSelectedNode() != null)
                    new LiteratureByYearDlg(getSelectedNode());
            }
        });
        findLitByPublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getSelectedNode() != null)
                    new SearchByPublisherOrAuthorDlg(getSelectedNode());
            }
        });
    }

    protected TreeModel getStartModel() throws Exception{
        Faculty f = new Faculty("NNIEIT", "Ivanets S.A.");
        Department d = new Department("ITSE", "Bilous", (short) 5);
        Subject s = new Subject("OOP","Byvoino T.P.");
        Literature l = new Literature("How to write a better code in Java using OOP", "Pinchuk S.S.", 2020, "Pinchuk Publisher House");

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(f);
        DefaultMutableTreeNode dNode = new DefaultMutableTreeNode(d);
        DefaultMutableTreeNode sNode = new DefaultMutableTreeNode(s);
        DefaultMutableTreeNode lNode = new DefaultMutableTreeNode(l);

        root.add(dNode); dNode.add(sNode); sNode.add(lNode);
        return (new JTree(root)).getModel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        MainForm mf = new MainForm();
        frame.setContentPane(mf.Pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        mf.onWindowOpened();
        frame.setVisible(true);
    }

    protected void onWindowOpened(){
        try{
            tree1.setModel(getStartModel());
            for (int i = 0; i < tree1.getRowCount(); i++)
                tree1.expandRow(i);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private DefaultMutableTreeNode getSelectedNode(){
        Object selectedNode = tree1.getLastSelectedPathComponent();
        if (selectedNode == null){
            JOptionPane.showMessageDialog(tree1, "Empty node", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return (DefaultMutableTreeNode) selectedNode;
    }

    private void expandAll(){
        for(int i = 0; i < tree1.getRowCount();i++)
            tree1.expandRow(i);
    }

    private void selectedNode(DefaultMutableTreeNode node){
        int n = 0;
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree1.getModel().getRoot();
        Enumeration<TreeNode> enm = root.children();
        while (enm.hasMoreElements()){
            DefaultMutableTreeNode nod = (DefaultMutableTreeNode) enm.nextElement();
            if (nod == node){
                tree1.setSelectionRow(n);
                return;
            }
            n++;
        }
    }

    protected void Clicked(MouseEvent e){
        if (e.getClickCount() != 3 || e.getButton() != MouseEvent.BUTTON3)
            return;
        DefaultMutableTreeNode node = getSelectedNode();
        if (node == null)
            return;
        CommonData data = (CommonData) node.getUserObject();
        Dlg dlg = data.showDialog(false);
        ((JDialog) dlg).dispose();
    }

    protected void onAdd(){
        DefaultMutableTreeNode parent = getSelectedNode();
        if (parent==null)return;
        CommonData parentData = (CommonData) parent.getUserObject();
        Dlg dlg = parentData.showSonDialog();
        if (dlg == null) return;
        Object obj;
        try {
            obj = dlg.createObject();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(tree1, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ((JDialog)dlg).dispose();
        if (obj==null)return;
        DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(obj);
        parent.add(newSon);
        tree1.updateUI();
        expandAll();
    }

    private void onErase(){
        DefaultMutableTreeNode node = getSelectedNode();
        if (node==null)return;
        node.removeFromParent();
        tree1.setSelectionPath(null);
        tree1.updateUI();
    }


    private void onEdit(){
        DefaultMutableTreeNode node = getSelectedNode();
        if (node == null) return;
        CommonData data = (CommonData) node.getUserObject();
        Dlg dlg = data.showDialog(true);
        Object obj;
        try{
            obj = dlg.createObject();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(tree1, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ((JDialog)dlg).dispose();
        if (obj == null) return;
        node.setUserObject(obj);
        tree1.updateUI();
    }


    private void onStore(){
        if (tree1.getModel() == null) return;
        JFileChooser fileChooser = new JFileChooser("Serialization");
        fileChooser.showSaveDialog(Pane);
        try{
            File f = fileChooser.getSelectedFile();
            String fName = f.getAbsolutePath();
            FileOutputStream fileStream = new FileOutputStream(fName);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            out.writeObject(tree1.getModel());
            out.close();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(tree1, "Error opening file", "Store", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tree1.setModel((new JTree()).getModel());
    }


    private void onRestore(){
        FileDialog fileDialog = new FileDialog(frame);
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        String dr = fileDialog.getDirectory();
        String fn = fileDialog.getFile();
        if (dr == null || fn == null) return;;
        String fName = dr + fn;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName));
            TreeModel model = (TreeModel) in.readObject();
            tree1.setModel(model);
            in.close();
        } catch (Exception ex){
            JOptionPane.showMessageDialog(tree1, "Deserialization error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        expandAll();
    }
}
