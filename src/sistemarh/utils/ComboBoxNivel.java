/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import java.awt.Component;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import sistemarh.DAO.CargoDAO;
import sistemarh.entidades.Cargo;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Alisson
 */
public class ComboBoxNivel extends AbstractCellEditor
        implements TableCellEditor {

    private JComboBox field;
    private List<Cargo> values = new ArrayList();
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        Object valueAt = ((TableFuncionarios)table.getModel()).getValueAt(row, 6);
        values = CargoDAO.carregarNiveis((Cargo)valueAt);

        field = new JComboBox();
      
        for (Cargo car : values) {
            field.addItem(car.getNivel());
        }
        
        field.setSelectedItem(1); //Deixa o editor pré-selecionado com o valor da célula  
        return field;
    }

    @Override
    public Object getCellEditorValue() {
        return parseInt((field.getSelectedItem()).toString());
    }
}
