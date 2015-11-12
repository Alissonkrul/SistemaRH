/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh.utils;

import sistemarh.entidades.Sistema;
import sistemarh.DAO.SistemaDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alisson
 */
public class TableSistemas extends AbstractTableModel {

    private List<Sistema> sistemas = new ArrayList();

    public void add(String name) {
        Sistema noveSistema = new Sistema(name);
        noveSistema.add();
        this.refreshTable();
    }

   private String[] colunas = {"Nome"};

    public TableSistemas() {
        sistemas = new ArrayList<>();
        refreshTable();
    }
    public void refreshTable() {
        sistemas = SistemaDAO.carregarSistemas();
        this.sort();
        fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.sistemas.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        refreshTable();
    }

    public void addRow(Sistema p) {
        this.sistemas.add(p);
        this.fireTableDataChanged();
        refreshTable();
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return sistemas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return true;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return sistemas.get(linha).getNome();
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object novoNome, int linhaIndex, int colunaIndex) {
        Sistema dep = sistemas.get(linhaIndex);
        dep.setNome(novoNome.toString());
        dep.update();
        this.refreshTable();
    }
    
    public void sort() {
        Collections.sort(sistemas, new Comparator<Sistema>() {
            public int compare(Sistema arg0, Sistema arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
    }

    public void delete(int linhas[]) throws Exception {
        for(int i=0; i< linhas.length; i++ )
        {       Sistema sistema = sistemas.get(linhas[i]);
            sistema.delete();
    }
        this.refreshTable();
    }

}
