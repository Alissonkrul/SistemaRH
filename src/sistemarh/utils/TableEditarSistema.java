/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sistemarh.DAO.SistemaDAO;
import sistemarh.entidades.Funcionario;
import sistemarh.entidades.Sistema;

/**
 *
 * @author Bruno Henrique
 */
public class TableEditarSistema extends AbstractTableModel {

    private List<Sistema> sistemas = new ArrayList();

    private String[] colunas = {"Nome"};

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    public void refreshTable(Funcionario funcionario) {
        sistemas = SistemaDAO.carregarSistemas(funcionario);
        //this.sort();
        fireTableDataChanged();
    }

    public void refreshTable() {
        sistemas = SistemaDAO.carregarSistemas();
        //this.sort();
        fireTableDataChanged();
    }

    public void add(String nome, Funcionario funcionario) {

        Sistema sistema = new Sistema();

        sistema.setNome(nome);
        sistema.add();

        this.refreshTable(funcionario);
    }

    public void add(String nome) {

        Sistema sistema = new Sistema();

        sistema.setNome(nome);
        sistema.add();

        this.refreshTable();
    }

    public List<Sistema> remove(int rows[], List<Sistema> sists) {
    List<Sistema> sistemas1 = new ArrayList();
        for (int i = 0; i < rows.length; i++) {
            sistemas1.add(sists.remove(rows[i] - i));
        }

        fireTableDataChanged();
        return sistemas1;
    }

    @Override
    public int getRowCount() {
        return sistemas.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return sistemas.get(rowIndex).getNome();
    }

    public void sort() {
        Collections.sort(sistemas, new Comparator<Sistema>() {
            public int compare(Sistema arg0, Sistema arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }

        });
    }

    public void addToList(List<Sistema> sists) {
        System.out.println(sists);
        for (Sistema sistema : sists) {
            sistemas.add(sistema);
        }
        this.sort();
        fireTableDataChanged();
    }

}
