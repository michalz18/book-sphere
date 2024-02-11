package com.example.view.components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JTable table;
    private final JButton button;
    private String label;
    private boolean isPushed;
    private final Consumer<Integer> action;

    public ButtonEditor(JCheckBox checkBox, Consumer<Integer> action) {
        super();
        this.button = new JButton();
        this.button.setOpaque(true);
        this.button.addActionListener(this);
        this.action = action;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        this.table = table;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            action.accept(table.convertRowIndexToModel(table.getEditingRow()));
        }
        isPushed = false;
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
    }
}
