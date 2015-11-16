/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import java.awt.Component;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import sistemarh.DAO.CargoDAO;
import sistemarh.entidades.Cargo;

/**
 *
 * @author Alisson
 */
public class ComboBoxCargo extends AbstractCellEditor
        implements TableCellEditor {

    private JComboBox field;
    private List<Cargo> values = CargoDAO.carregarCargos();

    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        field = new JComboBox();
        for(Cargo car: values)
            field.addItem(car);
        field.setSelectedItem(value); //Deixa o editor pré-selecionado com o valor da célula  
        return field;
    }

    @Override
    public Object getCellEditorValue() {
        return ((Cargo)field.getSelectedItem());
    }
}
