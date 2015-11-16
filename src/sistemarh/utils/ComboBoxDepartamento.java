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
import sistemarh.DAO.DepartamentoDAO;
import sistemarh.entidades.Departamento;

/**
 *
 * @author Alisson
 */
public class ComboBoxDepartamento extends AbstractCellEditor
        implements TableCellEditor {

    private JComboBox field;
    private List<Departamento> values = DepartamentoDAO.carregarDepartamentos();

    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {
        field = new JComboBox();
        for(Departamento dep: values)
            field.addItem(dep);
        field.setSelectedItem(value); //Deixa o editor pré-selecionado com o valor da célula  
        return field;
    }

    @Override
    public Object getCellEditorValue() {
        return ((Departamento)field.getSelectedItem());
    }
}
